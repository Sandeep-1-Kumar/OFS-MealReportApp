import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {
  private userData: string='';

  setUserData(userData: any) {
    this.userData = userData;
  }
  getUserData() {
    return this.userData;
  }
  constructor() { }
}
