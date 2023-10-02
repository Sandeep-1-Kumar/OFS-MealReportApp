import { Input, Component, Output, EventEmitter} from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-addadmin',
  templateUrl: './addadmin.component.html',
  styleUrls: ['./addadmin.component.scss']
})
export class AddadminComponent {
  show = false;
  showSuccessMessage = false;
  addAdminForm: FormGroup;
  constructor(private fb: FormBuilder ,
    private http: HttpClient,
    private snackBar: MatSnackBar) {
    // Initialize the form and define form controls with optional validators
    this.addAdminForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
  @Input() error: string | null=null;

  @Output() submitEM = new EventEmitter();
  submit() {
    // Check if the form is valid
    if (this.addAdminForm.valid) {
      this.show = true;
      const requestBody = {
        username: this.addAdminForm.value.username,
        password: this.addAdminForm.value.password,
      };
      const apiUrl = 'http://localhost:8080/OFS/admin/create';
      this.http.post(apiUrl, requestBody).subscribe(
        (response: any) => {
          // Handle success response
          this.showSuccessMessage = true;
          this.snackBar.open(response.statusMessage, 'Close', {
            duration: 3000, // Duration for which the success message will be displayed
          });
        },
        (error) => {
          // Handle error respons
          console.log(error)
          console.error('Error creating admin user: Admin alredy exist');
          // You can display an error message here if needed
        }
      );
    }
  }
}
