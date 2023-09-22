import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router ,ActivatedRoute} from '@angular/router';
import { UserDataService } from 'src/app/services/user-data.service';

@Component({
  selector: 'app-siteuser-dashboard',
  templateUrl: './siteuser-dashboard.component.html',
  styleUrls: ['./siteuser-dashboard.component.scss']
})
export class SiteuserDashboardComponent {
  currentDate: Date = new Date();
  currentTime: Date = new Date();
  userData: { username: string, id: number } = { username: '', id: 0 };

  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private userDataService: UserDataService) {}
  ngOnInit(): void {
    // Update the time every second
    this.route.params.subscribe(params => {
      // Retrieve user data from the service
      this.userData = this.userDataService.getUserData();
    });
    setInterval(() => {
      this.currentTime = new Date();
    }, 1000);
  }
}
