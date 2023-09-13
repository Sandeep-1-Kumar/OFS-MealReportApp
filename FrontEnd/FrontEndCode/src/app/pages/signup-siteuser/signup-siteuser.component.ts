import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient ,HttpHeaders } from '@angular/common/http';
import {Router} from '@angular/router';
import { NgxMaterialTimepickerComponent } from 'ngx-material-timepicker';
@Component({
  selector: 'app-signup-siteuser',
  templateUrl: './signup-siteuser.component.html',
  styleUrls: ['./signup-siteuser.component.scss']
})
export class SignupSiteuserComponent {
// Initialize with an empty FormGroup

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
    constructor(private fb: FormBuilder,private http: HttpClient,private router: Router) {
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
    this.registrationForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      siteName: ['', Validators.required],
      siteSupervisor: ['', Validators.required],
      siteAddress: ['', Validators.required],
      breakfastTime: [''],
      lunchTime: [''],
      supperTime: [''],
      snackTime: [''],
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
        lunchTime: this.stripAmPm(this.registrationForm.get('lunchTime')?.value) || '',
        supperTime: this.stripAmPm(this.registrationForm.get('supperTime')?.value) || '',
        snackTime: this.stripAmPm(this.registrationForm.get('snackTime')?.value) || '',
        
        mealDays: this.registrationForm.get('mealDays')?.value || '',
        adminid: 2
        // mon: this.registrationForm.get('mon')?.value || false,
        // tue: this.registrationForm.get('tue')?.value || false,
        // wed: this.registrationForm.get('wed')?.value || false,
        // thu: this.registrationForm.get('thu')?.value || false,
        // fri: this.registrationForm.get('fri')?.value || false,
        // sat: this.registrationForm.get('sat')?.value || false,
        // sun: this.registrationForm.get('sun')?.value || false,
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
    }
    stripAmPm(timeValue: string): string {
      // Remove "AM" or "PM" from the time value
      if (timeValue) {
        return timeValue.replace(/\s?[APap][Mm]/, '');
      }
      return '';
    }
    
    // onSubmit() {
      
    //   const selectedDays = Object.keys(this.selectedDays).filter(day => this.selectedDays[day]);
    //   this.formData.mealDays = selectedDays.join(', ');
    
    //   // Log the updated form data
    //   const submittedData = {
    //     username: this.formData.username,
    //     password: this.formData.password,
    //     email: this.formData.email,
    //     siteName: this.formData.siteName,
    //     siteSupervisor: this.formData.siteSupervisor,
    //     siteAddress: this.formData.siteAddress,
    //     breakfastTime: this.formData.breakfastTime,
    //     lunchTime: this.formData.lunchTime,
    //     supperTime: this.formData.supperTime,
    //     snackTime: this.formData.snackTime,
    //     mealDays: this.formData.mealDays,
    //   };
    //   console.log('Form data submitted:', submittedData);
    // }
    
}
