import { HttpClient } from '@angular/common/http';
import { Component ,ElementRef, OnInit, ViewChild ,AfterViewInit} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router ,ActivatedRoute} from '@angular/router';
import {FormBuilder, AbstractControl,FormGroup} from '@angular/forms'
import { MatSort } from '@angular/material/sort';
import { ExportAsConfig, ExportAsService } from 'ngx-export-as';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { UserDataService } from 'src/app/services/user-data.service';
import * as XLSX from 'xlsx';


import { UpdateadminpasswordComponent } from '../updateadminpassword/updateadminpassword.component';

import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth.service';
import { PdfViewerComponent } from '../pdf-viewer/pdf-viewer.component';
import { saveAs } from 'file-saver';
import * as FileSaver from 'file-saver';



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  @ViewChild('tableToExport', { static: false }) tableToExport!: ElementRef;


  currentDate: Date = new Date();
  currentTime: Date = new Date();
  startDate: Date = new Date();
endDate: Date = new Date();
pdfUrlMap: Map<MealCount, string> = new Map();
  tokenPayload: { username: string, userId: number } | null = null;
  headerHeight: number = 100;
  @ViewChild(MatSort, { static: false }) sort: MatSort | undefined;
  displayedColumns = ['siteName', 'mealdate', 'mealservicestatus','mealtype', 'previousdaymeals','mealsdelivered', 'mealsserved','damagedmeals', 'expiredmeals','eligiblecarryovermeals','comment','actions'];
  dataSource= new MatTableDataSource<MealCount>([]);
  siteNames: string[] = []; 
  filterDate = '';
  filterMealType = '';
  filterProgram = '';
  filterSiteUserId = '';
  filterSiteName='';
  

  openPDFPopup(pdfSrc: string,rowData: any) {
    console.log(rowData);
    this.UserDataService.setRowData(rowData);
    const dialogRef = this.dialog.open(PdfViewerComponent, {
      
      data: {  pdfSrc: pdfSrc, rowData: rowData },
      width: '50%',
      height: '80%'
    });
  
    // Optionally, you can subscribe to the dialog's close event.
    dialogRef.afterClosed().subscribe(result => {
      // Handle the close event if needed.
    });
  }
  
  
  public openPDF(): void {
    let DATA: any = document.getElementById('htmlData');
        console.log(DATA);
    html2canvas(DATA).then((canvas) => {
      let fileWidth = 208;
      let fileHeight = (canvas.height * fileWidth) / canvas.width;

      const FILEURI = canvas.toDataURL('image/png');
      let PDF = new jsPDF('p', 'mm', 'a4');
      let position = 0;
      PDF.addImage(FILEURI, 'PNG', 0, position, fileWidth, fileHeight);

      PDF.save('angular-demo.pdf');
    });
  }
  // ...

  applyFilter() {
    const queryParams = {
      date: this.filterDate,
      mealType: this.filterMealType,
      program: this.filterProgram,
      siteUserId: this.filterSiteUserId,
      siteName :this.filterSiteName
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

  getBackgroundColor(status: string) {
    if (status === 'completed') {
      return { 'background-color': '#FF0000' }; // Red background for 'Value1'
    } else if (status === 'in progress') {
      return { 'background-color': '#00FF00' }; // Green background for 'Value2'
    } else {
      return {}; // Default styling for other values
    }
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
  
  getSiteNames() {
    // Make an HTTP GET request to fetch site names
    this.http
      .get<string[]>('http://localhost:8080/OFS/siteuser/getAllSiteNames')
      .subscribe((data) => {
        this.siteNames = data;
      });
  }
  constructor(private http: HttpClient,  private exportAsService: ExportAsService,private UserDataService: UserDataService,private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder,private dialog: MatDialog,private authService: AuthService,) {
   
    };
    viewItem(item: any) {
      // You can implement your logic to display item details, e.g., in a dialog
      console.log('View item:', item);
      // Open a dialog or navigate to a view page with item details
    }
  
    // Function to handle editing an item
    editItem(item: any) {
      // You can implement your logic to edit the item, e.g., in a form
      console.log('Edit item:', item);
      // Open a dialog or navigate to an edit page with item details for editing
    }
   
    
  ngOnInit(): void {
    // this.dataSource.data.forEach(row => {
    //   this.pdfUrlMap.set(row, `https://example.com/${row.id}.pdf`); // Adjust the URL generation as needed
    // });
    const token = localStorage.getItem('token');
    this.getSiteNames();
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

 


  export() {
   
    let DATA: any = document.getElementById('htmlData');
  
    const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(DATA);
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws);
  
    // Generate a unique file name with the .csv extension
    const fileName = 'FileName.csv';
  
    XLSX.writeFile(wb, fileName);
  }
  
  
}
  export interface MealCount {
    siteName: string;
    mealdate: string;
    mealservicestatus:string;
    mealtype: string;
    previousdaymeals:number;
    damagedmeals: number;
    mealsserved: number;
    expiredmeals: number;
    eligiblecarryovermeals: number;
    mealsdelivered : number;
    comment: string;
    
  }





