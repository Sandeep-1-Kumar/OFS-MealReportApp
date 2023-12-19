import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router ,ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-siteuser-dashboard',
  templateUrl: './siteuser-dashboard.component.html',
  styleUrls: ['./siteuser-dashboard.component.scss']
})
export class SiteuserDashboardComponent {
  currentDate: Date = new Date();
  currentTime: Date = new Date();
  userData: { username: string, id: number } = { username: '', id: 0 };
  tokenPayload: { username: string, userId: number } | null = null;
  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute) {}
  ngOnInit(): void {
    // Update the time every second
    const token = localStorage.getItem('token');

    if (token) {
      // Decode the token to get the username and user ID
      this.tokenPayload = JSON.parse(atob(token));
      console.log( "token",this.tokenPayload?.username )
    }  
    
    setInterval(() => {
      this.currentTime = new Date();
    }, 1000);
  }
}
