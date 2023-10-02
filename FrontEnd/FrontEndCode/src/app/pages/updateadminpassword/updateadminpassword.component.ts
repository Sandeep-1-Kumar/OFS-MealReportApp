import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http'; 
@Component({
  selector: 'app-updateadminpassword',
  templateUrl: './updateadminpassword.component.html',
  styleUrls: ['./updateadminpassword.component.scss']
})
export class UpdateadminpasswordComponent {
  updatePasswordForm: FormGroup;
  isDisabled: boolean = true;
  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<UpdateadminpasswordComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient
  ) {
    this.updatePasswordForm = this.fb.group({
      username: [data.username],
      id: [data.id],
      newPassword: ['', Validators.required],
    });
  }
  onSubmit() {
    if (this.updatePasswordForm.valid) {
      // Perform the update password logic here
      // Send an HTTP PUT request to update the password

      // After a successful password update, you can close the dialog
      const apiUrl = `http://localhost:8080/OFS/admin/updatePassword/${this.data.id}`;
      const requestBody = { newPassword: this.updatePasswordForm.value.newPassword };

      // Send an HTTP PUT request to update the password
      this.http.put(apiUrl, requestBody).subscribe(
        (response: any) => {
          // Handle success response
          console.log('Password updated successfully:', response);

          // Show a success message
          alert('Password updated successfully');

          // Close the dialog
          this.dialogRef.close();
        },
        (error) => {
          // Handle error response
          console.error('Error updating password:', error);

          // Show an error message (you can customize this)
          alert('Error updating password');
      this.dialogRef.close();
        }
      );
    }
  }

}
