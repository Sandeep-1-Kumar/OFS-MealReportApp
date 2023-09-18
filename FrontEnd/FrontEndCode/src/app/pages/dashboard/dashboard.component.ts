import { HttpClient } from '@angular/common/http';
import { Component , OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router ,ActivatedRoute} from '@angular/router';


import { UserDataService } from 'src/app/services/user-data.service';
interface MealCount {
  username: string;
  mealDate: string;
  mealType: string;
  program: string;
  mealCount: number;
  comment: string;
} 
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  

  currentDate: Date = new Date();
  currentTime: Date = new Date();
  userData: string='';
  filterValue: string = '';
  displayedColumns: string[] = ['username', 'mealDate', 'mealType', 'program', 'mealCount', 'comment'];
  dataSource: MatTableDataSource<MealCount> = new MatTableDataSource<MealCount>([]); // Provide an empty array


  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private userDataService: UserDataService) {}
  applyFilter(filterValue: string) {
    filterValue = (filterValue || '').trim().toLowerCase(); // Ensure filterValue is a string
    this.dataSource.filter = filterValue;
  }
  
  
  ngOnInit(): void {
    // Update the time every second
    this.route.params.subscribe(params => {
      // Retrieve user data from the service
      this.userData = this.userDataService.getUserData();
    });


    

    setInterval(() => {
      this.currentTime = new Date();
    }, 1000);

    this.http.get<MealCount[]>('http://localhost:8080/OFS/admin/getMealCounts').subscribe(
      (mealCounts) => {
        // Set the data to the dataSource
        console.log('Meal Counts:', mealCounts); 
        this.dataSource.data = mealCounts;
      },
      (error) => {
        console.error('Error fetching meal counts:', error);
      }
    );
  }
  
  
}
