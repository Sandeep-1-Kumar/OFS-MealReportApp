import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  currentDate: Date = new Date();
  currentTime: Date = new Date();
  constructor() {}

  
  ngOnInit(): void {
    // Update the time every second
    setInterval(() => {
      this.currentTime = new Date();
    }, 1000);
  }
}
