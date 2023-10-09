import { HttpClient } from '@angular/common/http';
import { Component , OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router ,ActivatedRoute} from '@angular/router';
import {FormBuilder, AbstractControl,FormGroup} from '@angular/forms'
import { MatSort } from '@angular/material/sort';

import { UpdateadminpasswordComponent } from '../updateadminpassword/updateadminpassword.component';

import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  currentDate: Date = new Date();
  currentTime: Date = new Date();
  startDate: Date = new Date();
endDate: Date = new Date();

  tokenPayload: { username: string, userId: number } | null = null;
  headerHeight: number = 100;
  @ViewChild(MatSort, { static: false }) sort: MatSort | undefined;
  displayedColumns = ['siteUserName', 'mealdate', 'mealtype', 'program', 'mealcount', 'comment','siteuserid'];
  dataSource= new MatTableDataSource<MealCount>([]);
  
  filterDate = '';
  filterMealType = '';
  filterProgram = '';
  filterSiteUserId = '';
  applyFilter() {
    const queryParams = {
      date: this.filterDate,
      mealType: this.filterMealType,
      program: this.filterProgram,
      siteUserId: this.filterSiteUserId,
    };

    // Construct the URL with query parameters
    const apiUrl = 'http://localhost:8080/OFS/admin/getMealCounts';
    const urlWithParams = this.addQueryParams(apiUrl, queryParams);

    // Fetch data with the updated URL
    this.http.get<MealCount[]>(urlWithParams).subscribe(
      (mealCounts) => {
        this.dataSource.data = mealCounts;
      },
      (error) => {
        console.error('Error fetching meal counts:', error);
      }
    );
  }

  // Function to add query parameters to a URL
  addQueryParams(url: string, params: { [key: string]: string }) {
    const queryString = Object.keys(params)
      .map((key) => key + '=' + params[key])
      .join('&');
    return url + '?' + queryString;
  }
  onLogoutClick() {
    this.authService.logout(); // Call the logout method from AuthService
    // Optionally, you can navigate the user to the login page here if needed
     this.router.navigate(['/login']);
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
  

  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder,private dialog: MatDialog,private authService: AuthService,) {
   
    };
    
  ngOnInit(): void {
    const token = localStorage.getItem('token');

    if (token) {
      // Decode the token to get the username and user ID
      this.tokenPayload = JSON.parse(atob(token));
      console.log( "token",this.tokenPayload?.username )
    }  

    if(this.tokenPayload){
      console.log( "token11",this.tokenPayload.username )
    }
    
          setInterval(() => {
            this.currentTime = new Date();
          }, 1000);
          
          this.http.get<MealCount[]>('http://localhost:8080/OFS/admin/getMealCounts')
          .subscribe(
            (mealCounts) => {
              console.log(mealCounts);
              this.dataSource.data = mealCounts;  
              console.log(this.dataSource.data);
              
            },
            (error) => {
              console.error('Error fetching meal counts:', error);
            }
          );


  }

 



  
  
}
  export interface MealCount {
    siteUserName: string;
    mealdate: string;
    mealtype: string;
    program: string;
    mealcount: number;
    comment: string;
    siteuserid : string;
  }





