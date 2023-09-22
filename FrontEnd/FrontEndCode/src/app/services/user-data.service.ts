import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {
  private userData: { username: string, id: number } = { username: '', id: 0 };

  setUserData(userData: { username: string, id: number }) {
    this.userData = userData;
  }
  getUserData() {
    return this.userData;
  }
  constructor() { }
}
