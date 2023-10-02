import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router ,ActivatedRoute} from '@angular/router';
import {FormBuilder, AbstractControl,FormGroup} from '@angular/forms'
import { MatSort } from '@angular/material/sort';
import { UserDataService } from 'src/app/services/user-data.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { EditpasswordComponent } from '../editpassword/editpassword.component';
export interface SiteUSer {
  username: string;
  id : string;

}
@Component({
  selector: 'app-edit-siteuser',
  templateUrl: './edit-siteuser.component.html',
  styleUrls: ['./edit-siteuser.component.scss']
})
export class EditSiteuserComponent {
  headerHeight: number = 100;
  dataSource= new MatTableDataSource<SiteUSer>([]);
  displayedColumns = ['username', 'id','actions'];
  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private userDataService: UserDataService,private formBuilder: FormBuilder, private snackBar: MatSnackBar,private dialog: MatDialog) {
  };

  ngOnInit(): void {
   
      
      this.http.get<SiteUSer[]>('http://localhost:8080/OFS/admin/getAllSiteUsers')
      .subscribe(
        (SiteUSers) => {
          console.log(SiteUSers);
          this.dataSource.data = SiteUSers;  
          console.log(this.dataSource.data);
          
        },
        (error) => {
          console.error('Error fetching meal counts:', error);
        }
      );


}
onEditClick(element: SiteUSer): void {
  // Implement edit functionality here, e.g., navigate to edit page
  console.log('Edit clicked for:', element);
}
openEditDialog(userId: number) {
  const dialogRef = this.dialog.open(EditpasswordComponent, {
    width: '300px', // Adjust the dialog width as needed
    data: { userId },
  });

  dialogRef.afterClosed().subscribe((result: string) => {
    if (result) {
      // Send an API request to update the password
      console.log()
      this.updatePassword(userId, result);
    }
  });
}
updatePassword(userId: number, newPassword: string) {
  // Make an HTTP PUT request to update the password
  const apiUrl = `http://localhost:8080/OFS/siteuser/updatePassword/${userId}`;
  const requestBody = { newPassword };

  // Send the request using HttpClient
  this.http.put(apiUrl, requestBody).subscribe(
    (response) => {
      // Handle success response
      console.log('Password updated successfully:', response);
      // You can update the user's password in your data source if needed
    },
    (error) => {
      // Handle error response
      console.error('Error updating password:', error);
    }
  );
}
// Delete button click handler
onDeleteClick(userId: number): void {

  // Make an HTTP DELETE request to delete the site user
  const apiUrl = `http://localhost:8080/OFS/siteuser/delete/${userId}`;

  // Send the request using HttpClient
  this.http.delete(apiUrl).subscribe(
    () => {
      // Handle success response
      console.log('Site user deleted successfully.');
      // Remove the user from your data source if needed
      this.snackBar.open('Site user deleted successfully', 'Dismiss', {
        duration: 3000, // Adjust the duration as needed (in milliseconds)
        panelClass: 'success-snackbar', // Add custom CSS class for styling
      });

    
    },
    (error) => {
      // Handle error response
      console.error('Error deleting site user:', error);
    }
  );
}
}