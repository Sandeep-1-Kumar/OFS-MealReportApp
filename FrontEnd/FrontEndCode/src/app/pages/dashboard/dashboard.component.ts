import { HttpClient } from '@angular/common/http';
import { Component , OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router ,ActivatedRoute} from '@angular/router';

import { MatSort } from '@angular/material/sort';
import { UserDataService } from 'src/app/services/user-data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  currentDate: Date = new Date();
  currentTime: Date = new Date();
  userData: { username: string, id: number } = { username: '', id: 0 };
  @ViewChild(MatSort, { static: false }) sort: MatSort | undefined;

  displayedColumns = ['username', 'mealDate', 'mealType', 'program', 'mealCount', 'comment'];
  dataSource = new MatTableDataSource<MealCount>([]);
  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private userDataService: UserDataService) {}
 

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }


  
  ngOnInit(): void {
    
    if (this.sort) {
      this.dataSource.sort = this.sort;
    }
  this.userData = this.userDataService.getUserData();
   

    setInterval(() => {
      this.currentTime = new Date();
    }, 1000);
    

    this.http.get<MealCount[]>('http://localhost:8080/OFS/admin/getMealCounts')
    .subscribe(
      (mealCounts) => {
        console.log(mealCounts);
        this.dataSource.data = mealCounts;
      },
      (error) => {
        console.error('Error fetching meal counts:', error);
      }
    );
  }
  
  
}
export interface MealCount {
  username: string;
  mealDate: string;
  mealType: string;
  program: string;
  mealCount: number;
  comment: string;
}



