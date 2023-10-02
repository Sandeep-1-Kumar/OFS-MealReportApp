import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-editpassword',
  templateUrl: './editpassword.component.html',
  styleUrls: ['./editpassword.component.scss']
})
export class EditpasswordComponent {
  newPassword: string = '';
  constructor(
    public dialogRef: MatDialogRef<EditpasswordComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { userId: number }
  ) {}

  onSubmit() {
    // Check if a new password is provided
    if (this.newPassword) {
      // Return the new password to the parent component
      console.log(this.newPassword);  
      this.dialogRef.close(this.newPassword);
    } else {
      // Optionally, you can display an error message here
    }
}
}
