import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient ,HttpHeaders } from '@angular/common/http';
import {Router} from '@angular/router';
import { NgxMaterialTimepickerComponent } from 'ngx-material-timepicker';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { SuccessDialogComponent } from 'src/app/services/success-dialog/success-dialog.component';

import { UserDataService } from 'src/app/services/user-data.service';
@Component({
  selector: 'app-signup-siteuser',
  templateUrl: './signup-siteuser.component.html',
  styleUrls: ['./signup-siteuser.component.scss']
})
export class SignupSiteuserComponent {
// Initialize with an empty FormGroup
userData: { username: string, id: number } = { username: '', id: 0 };
    //time: { hour: number, minute: number } = { hour: 12, minute: 0 }; // Define the time variable
    registrationForm!: FormGroup;
    breakfastTime: string='1:06 AM';
    lunchTime: string;
    supperTime: string;
    snackTime: string;
    dayMapping: { [key: string]: string } = {
      mon: 'Monday',
      tue: 'Tuesday',
      wed: 'Wednesday',
      thu: 'Thursday',
      fri: 'Friday',
      sat: 'Saturday',
      sun: 'Sunday',
    };
    constructor(private fb: FormBuilder,private http: HttpClient,private router: Router,public dialog: MatDialog,private userDataService: UserDataService) {
      // Initialize time properties
      this.breakfastTime = '';
      this.lunchTime = '';
      this.supperTime = '';
      this.snackTime = '';
    }
  
    @ViewChild('breakfastTimepicker') breakfastTimepicker!: NgxMaterialTimepickerComponent;
  @ViewChild('lunchTimepicker') lunchTimepicker!: NgxMaterialTimepickerComponent;
  @ViewChild('supperTimepicker') supperTimepicker!: NgxMaterialTimepickerComponent;
  @ViewChild('snackTimepicker') snackTimepicker!: NgxMaterialTimepickerComponent;

  // ... rest of your code ...
  
    
    
    days: string[] = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
    selectedDaysText: string = '';
  selectedDays: any = {
    mon: false,
    tue: false,
    wed: false,
    thu: false,
    fri: false,
    sat: false,
    sun: false
  };
  


  ngOnInit(): void {
    this.userData = this.userDataService.getUserData();
    this.registrationForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      siteName: ['', Validators.required],
      siteSupervisor: ['', Validators.required],
      siteAddress: ['', Validators.required],
      breakfastStartTime: [''],
      breakfastEndTime: [''],
      lunchStartTime: [''],
      lunchEndTime: [''],
      supperStartTime: [''],
      supperEndTime: [''],
      snackStartTime: [''],
      snackEndTime: [''],
      mealDays: [''],
      Monday: [false], // Initialize with 'false' if not selected
    Tuesday: [false],
    Wednesday: [false],
    Thursday: [false],
    Friday: [false],
    Saturday: [false],
    Sunday: [false],
      // Add form controls for selectedDays here
    });}
    
    onSubmit() {
      const selectedDaysArray = Object.keys(this.registrationForm.value).filter(
        (day) => this.registrationForm.value[day] === true
      );
    
      // Format the selectedDaysArray as a comma-separated string
      const formattedMealDays = selectedDaysArray.join(', ');
    
      // Set the formatted mealDays value in the form control
      this.registrationForm.patchValue({ mealDays: formattedMealDays });
      const customFormData = {
        username: this.registrationForm.get('username')?.value || '',
        email: this.registrationForm.get('email')?.value || '',
        password: this.registrationForm.get('password')?.value || '',
        siteName: this.registrationForm.get('siteName')?.value || '',
        siteSupervisor: this.registrationForm.get('siteSupervisor')?.value || '',
        siteAddress: this.registrationForm.get('siteAddress')?.value || '',
        breakfastStartTime: this.stripAmPm(this.registrationForm.get('breakfastStartTime')?.value) || '',
        breakfastEndTime: this.stripAmPm(this.registrationForm.get('breakfastEndTime')?.value) || '',
        lunchStartTime: this.stripAmPm(this.registrationForm.get('lunchStartTime')?.value) || '',
        lunchEndTime: this.stripAmPm(this.registrationForm.get('lunchEndTime')?.value) || '',
        supperStartTime: this.stripAmPm(this.registrationForm.get('supperStartTime')?.value) || '',
        supperEndTime: this.stripAmPm(this.registrationForm.get('supperEndTime')?.value) || '',
        snackStartTime: this.stripAmPm(this.registrationForm.get('snackStartTime')?.value) || '',
        snackEndTime: this.stripAmPm(this.registrationForm.get('snackEndTime')?.value) || '',
        
        mealDays: this.registrationForm.get('mealDays')?.value || '',
        adminid: this.userData.id
        
      };
      console.log('Submitted Data:', customFormData);
      this.http.post('http://localhost:8080/OFS/siteuser/create', customFormData).subscribe(
    (response) => {
      console.log('Server response:', response);
    },
    (error) => {
      console.error('Error:', error);
    }
  );
  const dialogRef = this.dialog.open(SuccessDialogComponent, {
    width: '250px', // Adjust the width as needed
  });

  // Handle dialog close event (optional)
  dialogRef.afterClosed().subscribe((result) => {
    console.log('The dialog was closed');
  });
    }
    stripAmPm(timeValue: string): string {
      // Remove "AM" or "PM" from the time value
      if (timeValue) {
        return timeValue.replace(/\s?[APap][Mm]/, '');
      }
      return '';
    }
    
    
    
}
