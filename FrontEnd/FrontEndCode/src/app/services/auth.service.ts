import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  constructor() { }
  logout() {
    localStorage.removeItem('token');
  }
  isAuthenticated(): boolean {
    // Get the token from localStorage
    const token = localStorage.getItem('token');
  
    // Check if the token is present and not expired
    if (token) {
  
      return true; // User is authenticated
    }
  
    return false; // User is not authenticated (no valid token found)
  }
  
}
