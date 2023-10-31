import { Component } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { Router ,ActivatedRoute} from '@angular/router';
import {FormBuilder, AbstractControl,FormGroup} from '@angular/forms'
import { MatSort } from '@angular/material/sort';

import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { EditpasswordComponent } from '../editpassword/editpassword.component';
import { AuthService } from 'src/app/services/auth.service';
import { UpdateadminpasswordComponent } from '../updateadminpassword/updateadminpassword.component';



export interface admin {
  username: string;
  id : string;

}
@Component({
  selector: 'app-deleteadmin',
  templateUrl: './deleteadmin.component.html',
  styleUrls: ['./deleteadmin.component.scss']
})


export class DeleteadminComponent {
  headerHeight: number = 100;
  userData: { username: string, id: number } = { username: '', id: 0 };
  dataSource= new MatTableDataSource<admin>([]);
  displayedColumns = ['username', 'id','actions'];
  tokenPayload: { username: string, userId: number } | null = null;
  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder, private snackBar: MatSnackBar,private dialog: MatDialog,private authService: AuthService,) {
  };
  ngOnInit(): void {
   
    
    this.http.get<admin[]>(`http://localhost:8080/OFS/admin/getAllAdmins/${this.userData.id}`)
    .subscribe(
      (admins) => {
        console.log(admins);
        console.log(this.userData.id)
        this.dataSource.data = admins;  
        console.log(this.dataSource.data);
        
      },
      (error) => {
        console.error('Error fetching meal counts:', error);
      }
    );


}
openUpdatePasswordDialog() {
  const dialogRef = this.dialog.open(UpdateadminpasswordComponent, {
    width: '300px',
    data: {
      username: this.tokenPayload?.username , // Pass the username
      id: this.tokenPayload?.userId , // Pass the user ID
    },
  });

  dialogRef.afterClosed().subscribe(() => {
    // Handle any logic after the dialog is closed
  });
}
onDeleteClick(adminId: string) {
  // Make an HTTP DELETE request to delete the admin with the specified ID
  const apiUrl = `http://localhost:8080/OFS/admin/delete/${adminId}`;

  // Send the request using HttpClient
  this.http.delete(apiUrl).subscribe(
    () => {
      // Handle success response
      console.log('Admin deleted successfully.');
      this.snackBar.open('Admin deleted successfully', 'Close', {
        duration: 2000, // Duration in milliseconds
      });
      // Refresh the admin data after deletion
      this.refreshAdminData();
    },
    (error) => {
      // Handle error response
      console.error('Error deleting admin:', error);
    }
  );
}

// Function to refresh admin data after deletion
refreshAdminData() {
  this.http.get<admin[]>(`http://localhost:8080/OFS/admin/getAllAdmins/${this.userData.id}`)
    .subscribe(
      (admins) => {
        this.dataSource.data = admins;
      },
      (error) => {
        console.error('Error fetching admin data:', error);
      }
    );
}

}
