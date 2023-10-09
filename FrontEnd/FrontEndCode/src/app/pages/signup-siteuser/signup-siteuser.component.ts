import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient ,HttpHeaders } from '@angular/common/http';
import {Router} from '@angular/router';
import { NgxMaterialTimepickerComponent } from 'ngx-material-timepicker';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { SuccessDialogComponent } from 'src/app/services/success-dialog/success-dialog.component';

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
    tokenPayload: { username: string, userId: number } | null = null;
    dayMapping: { [key: string]: string } = {
      mon: 'Monday',
      tue: 'Tuesday',
      wed: 'Wednesday',
      thu: 'Thursday',
      fri: 'Friday',
      sat: 'Saturday',
      sun: 'Sunday',
    };
    constructor(private fb: FormBuilder,private http: HttpClient,private router: Router,public dialog: MatDialog) {
      // Initialize time properties
      
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
    const token = localStorage.getItem('token');

    if (token) {
      // Decode the token to get the username and user ID
      this.tokenPayload = JSON.parse(atob(token));
      console.log( "token",this.tokenPayload?.username )
    }  
    
    this.registrationForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      siteName: ['', Validators.required],
      siteSupervisor: ['', Validators.required],
      siteAddress: ['', Validators.required],
      breakfastTime: [''],
      breakfastEndTime: [''],
      lunchTime: [''],
      lunchEndTime: [''],
      supperTime: [''],
      supperEndTime: [''],
      snackTime: [''],
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
        breakfastTime: this.stripAmPm(this.registrationForm.get('breakfastTime')?.value) || '',
        breakfastEndTime: this.stripAmPm(this.registrationForm.get('breakfastEndTime')?.value) || '',
        lunchTime: this.stripAmPm(this.registrationForm.get('lunchTime')?.value) || '',
        lunchEndTime: this.stripAmPm(this.registrationForm.get('lunchEndTime')?.value) || '',
        supperTime: this.stripAmPm(this.registrationForm.get('supperTime')?.value) || '',
        supperEndTime: this.stripAmPm(this.registrationForm.get('supperEndTime')?.value) || '',
        snackTime: this.stripAmPm(this.registrationForm.get('snackTime')?.value) || '',
        snackEndTime: this.stripAmPm(this.registrationForm.get('snackEndTime')?.value) || '',
        
        mealDays: this.registrationForm.get('mealDays')?.value || '',
        adminid: this.tokenPayload?.userId
        
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
