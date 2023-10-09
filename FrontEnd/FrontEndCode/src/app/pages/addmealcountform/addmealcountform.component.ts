import { Component ,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {NgFor, NgIf} from '@angular/common';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
@Component({
  selector: 'app-addmealcountform',
  templateUrl: './addmealcountform.component.html',
  styleUrls: ['./addmealcountform.component.scss']
})
export class AddmealcountformComponent {
  personalDetails!: FormGroup;
  addressDetails!: FormGroup;
  registrationForm!: FormGroup;
  deliveryDetailsForm!: FormGroup;
  reviewForm!:FormGroup;
  reviewForm2!:FormGroup;
  deliveryReceived: string | null = null;
  personal_step = false;
  address_step = false;
  mealtimes: string[] = ['lunch', 'breakfast', 'supper', 'snack'];
  step = 1;
  constructor(private formBuilder: FormBuilder) {}
  ngOnInit() {
    this.personalDetails = this.formBuilder.group({
      siteName: ['' ],
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
    
  }
  get personal() {
    return this.personalDetails.controls;
  }
  setDelivery(choice: string) {
    this.deliveryReceived = choice;
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
      if (this.deliveryDetailsForm.invalid) {
        // Handle validation errors or display a message to the user for deliverDetails form.
        return;
      }
      this.step++; // Move to the next step when the form is valid.
    }else if (this.step === 3) {
      if (this.reviewForm.invalid) {
        // Handle validation errors or display a message to the user for deliverDetails form.
        return;
      }
      this.step++; // Move to the next step when the form is valid.
    }
    
  }

  previous() {
    this.step--;
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
