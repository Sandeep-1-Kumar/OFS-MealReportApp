import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient ,HttpHeaders } from '@angular/common/http';


import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string= '';
  constructor(private http: HttpClient,private router: Router) { }

  

  ngOnInit() {
  }
  login(): void {
    if (this.username && this.password) {
      const signInRequest = {
        username: this.username,
        password: this.password
      };

      // Define HTTP headers if needed, e.g., for content type
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });

      this.http.post<any>('http://localhost:8080/OFS/admin/signIn', signInRequest, { headers }).subscribe(
  (response) => {
    if (response.statusCode === '200') {
      console.log(response.statusCode);
      // Login successful, navigate to Dashboard
      this.router.navigate(['Dashboard']);
    } else {
      console.log('Login failed:11', response.statusCode)
      console.error('Login failed:', response);
      alert('Invalid username or password.');
    }
  },
  (error) => {
    console.error('Error while signing in:', error);
    alert('Error during sign-in. Please try again later.');
  }
);

    }
  }
  
  // loginForm: FormGroup;
  // submitted = false;
  // error = '';

  // constructor(private formBuilder: FormBuilder, private http: HttpClient) {
  //   this.loginForm = this.formBuilder.group({
  //     username: ['', Validators.required],
  //     password: ['', Validators.required]
  //   });
  // }

  // get f() {
  //   return this.loginForm.controls;
  // }

  // onSubmit() {
  //   this.submitted = true;
  
  //   if (this.loginForm.invalid) {
  //     return;
  //   }
  
  //   const formData = {
  //     username: this.f['username'].value,
  //     password: this.f['password'].value
  //   };
  
  //   this.http.post('http://localhost:3000/admins', formData)
  //     .subscribe(
  //       (response) => {
  //         // Handle successful login
  //         console.log('Login successful:', response);
  //         // You might want to store tokens or user info in your app's state
  //       },
  //       (error) => {
  //         this.error = 'Invalid username or password';
  //         console.error('Login failed:', error);
  //       }
  //     );
  // }
  
}
