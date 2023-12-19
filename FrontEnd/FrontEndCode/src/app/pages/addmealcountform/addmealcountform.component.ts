import { Component ,OnInit ,ElementRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import { SignaturePadComponent } from '../signature-pad/signature-pad.component';
@Component({
  selector: 'app-addmealcountform',
  templateUrl: './addmealcountform.component.html',
  styleUrls: ['./addmealcountform.component.scss']
})
export class AddmealcountformComponent {
 
  openSignatureModal() {
    const dialogRef = this.dialog.open(SignaturePadComponent);

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        // Handle the saved signature result if needed.
      }
    });
  }
  personalDetails!: FormGroup;
  addressDetails!: FormGroup;
  registrationForm!: FormGroup;
  deliveryDetailsForm!: FormGroup;
  reviewForm!:FormGroup;
  reviewForm2!:FormGroup;
  reviewForm3!:FormGroup;
  reviewForm4!:FormGroup;
  siteNameErrorMessage: string = '';
  date1: Date = new Date();
  tokenPayload: { username: string, userId: number } | null = null;
  deliveryReceived: string | null = null;
  personal_step = false;
  address_step = false;
  mealtimes: string[] = ['lunch', 'breakfast', 'supper', 'snack'];
  step = 1;
  // Define temporary values
numMealsDeliveredTemp: string = '5';
numMealsPreviousDayTemp: string = '10';

  constructor(private formBuilder: FormBuilder,private http: HttpClient,private dialog: MatDialog) {}
  ngOnInit() {
    const token = localStorage.getItem('token');

    if (token) {
      // Decode the token to get the username and user ID
      this.tokenPayload = JSON.parse(atob(token));
  
    }  
    const userId = this.tokenPayload?.userId;
    this.personalDetails = this.formBuilder.group({
      siteName: [{ value: '', disabled: true }, Validators.required],
      mealType: [''], // Set the default value to 'lunch'
      date: [''],
      fullName: [''],
    });
    this.deliveryDetailsForm = this.formBuilder.group({
      deliveryReceived: ['no'], // Initialize with 'no'
      numMealsDelivered: [''],
      deliveryTime: [''],
      mealTemperature: [''],
      mealServiceTime: [''],
      numMealsPreviousDay: [''], // Add default value if needed
    });
    this.reviewForm = this.formBuilder.group({
      deliveryDate: ['no'], // Initialize with 'no'
      mealType: [''],
      deliveryStats: [''],
      mealTemperature: [''],
      mealServiceTime: [''],

    });
    this.reviewForm2 = this.formBuilder.group({
      deliveryDate: ['no'], // Initialize with 'no'
      mealType: [''],
      deliveryStats: [''],
      mealTemperature: [''],
      mealServiceTime: [''],

    });
    this.reviewForm3 = this.formBuilder.group({
      mealsServed: [''], // Initialize with an empty string or default value
      damagedMeals: [''], // Initialize with an empty string or default value
      damagedMealsComment: [''],
      damagedMealsImage: [''],
    });
    this.reviewForm4 = this.formBuilder.group({
      expiredMealCount: [''], // Initialize with an empty string or default value
      expiredMealComment: [''],
    });
    const deliveryReceivedControl = this.deliveryDetailsForm.get('deliveryReceived');
  
    if (deliveryReceivedControl) {
      deliveryReceivedControl.valueChanges.subscribe(value => {
        if (value === 'yes') {
          // Clear the fields for 'No' option
          this.deliveryDetailsForm.get('numMealsPreviousDay')!.setValue('');
          this.deliveryDetailsForm.get('mealServiceTime')!.setValue('');
          this.deliveryDetailsForm.get('mealTemperature')!.setValue('');
        } else if (value === 'no') {
          // Clear the fields for 'Yes' option
          this.deliveryDetailsForm.get('numMealsDelivered')!.setValue('');
          this.deliveryDetailsForm.get('deliveryTime')!.setValue('');
          this.deliveryDetailsForm.get('mealServiceTime')!.setValue('');
          this.deliveryDetailsForm.get('mealTemperature')!.setValue('');
        }
      });
    }
    // You can change the siteId as needed
    console.log(userId);
    const apiUrl = `http://localhost:8080/OFS/siteuser/getSiteName/${userId}`;
    this.http
      .get(apiUrl, { responseType: 'text' })
      .subscribe(
        (siteName: string) => {
          if (siteName) {
            this.personalDetails.get('siteName')?.setValue(siteName);
          } else {
            // Set an error message if siteName is null
            this.siteNameErrorMessage = 'Site name is not valid';
          }
        },
        (error) => {
          // Handle HTTP request errors if needed
          console.error('HTTP error:', error);
          this.siteNameErrorMessage = 'Failed to fetch site name';
        }
      );
    
  }
  get personal() {
    return this.personalDetails.controls;
  }
 
  setDelivery(choice: string) {
    this.deliveryReceived = choice;
    this.deliveryDetailsForm.get('deliveryReceived')!.setValue(choice);
    console.log(choice)
  }

  get address() {
    return this.addressDetails.controls;
  }
  concatenatedValues(): string {
    // Concatenate values from personalDetails and deliveryDetailsForm
    const personalDetailsValues = this.personalDetails.value;
    const deliveryDetailsValues = this.deliveryDetailsForm.value;
  
    const concatenated = `Personal Details: siteName - ${personalDetailsValues.siteName},
     mealType - ${personalDetailsValues.mealType}\n` +
      `Delivery Details: numMealsDelivered - ${deliveryDetailsValues.numMealsDelivered}, 
      deliveryTime - ${deliveryDetailsValues.deliveryTime}`;
  
    return concatenated;
  }

  next() {
    if (this.step === 1) {
      if (this.personalDetails.invalid) {
        // Handle validation errors or display a message to the user.
        return;
      }
      this.step++; // Move to the next step when the form is valid.
    } else if (this.step === 2) {
      const formValues = this.deliveryDetailsForm.value;
      if (this.deliveryReceived === 'yes') {
        // Send the form data for "Yes"
       console.log(formValues);
      } else if (this.deliveryReceived === 'no') {
        // Send the form data for "No"
        console.log(formValues);
      }
  
      this.step++; // Move to the next step when the form is valid.
    }else if (this.step === 3) {
      console.log(this.step);
      if (this.reviewForm.invalid) {
        // Handle validation errors or display a message to the user for deliverDetails form.
        console.log(this.step);
        return;
      }
      this.step++; // Move to the next step when the form is valid.
    }else if (this.step === 4) {
      console.log(this.step)
      // if (this.reviewForm2.invalid) {
      //   // Handle validation errors or display a message to the user for deliverDetails form.
      //   return;
      // }
      this.step++; // Move to the next step when the form is valid.
    }
    else if (this.step === 5) {
      console.log(this.step)
      // if (this.reviewForm2.invalid) {
      //   // Handle validation errors or display a message to the user for deliverDetails form.
      //   return;
      // }
      this.step++; // Move to the next step when the form is valid.
    }
    
  }

  previous() {
    this.step--;
    if (this.step === 2) {
      // If you are coming back from step 3 to step 2
      this.deliveryReceived = this.deliveryDetailsForm.get('deliveryReceived')!.value;
    }
    if (this.step == 1) {
      this.personal_step = false;
    }
  }
  // Helper functions with null checks
  isSiteNameInvalid(): boolean {
    return !!this.personalDetails.get('siteName')?.invalid;
  }

  isDateInvalid(): boolean {
    return !!this.personalDetails.get('date')?.invalid;
  }

  isFullNameInvalid(): boolean {
    return !!this.personalDetails.get('fullName')?.invalid;
  }
  onSubmit() {}

  submit() {
    if (this.step == 2) {
      this.address_step = true;
      if (this.addressDetails.invalid) {
        return;
      }
    }
  }
}
