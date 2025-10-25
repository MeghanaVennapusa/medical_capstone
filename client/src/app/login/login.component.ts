import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';
import { AuthService } from '../../services/auth.service';
import { catchError, of, tap } from 'rxjs';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  ItemForm!:FormGroup;
  errorMessage: string="";
  successMessage:string="";
  constructor(private fb:FormBuilder,private authService:AuthService,private router:Router,private httpService:HttpService)
  {

  }
  ngOnInit(): void {
  
    this.ItemForm=this.fb.group(
      {
        username:["",Validators.required],
        password:["",Validators.required]
      }
    );
  }
  onSubmit(): void {
    if (this.ItemForm.valid) {
      const formData = this.ItemForm.value;
      this.httpService.login(formData).pipe(
        tap((response) => {
          console.log(response);
  
          // Save token using AuthService
          this.authService.saveToken(response.token);
  
          // Save other user details 
          localStorage.setItem("role", response.roles);
          localStorage.setItem("user_id", response.userId);
  
          console.log(localStorage.getItem("role"));
  
          // Navigate to dashboard
          this.router.navigate(["dashboard"]);
        }),
        catchError((error) => {
          this.errorMessage = 'Invalid username or password';
          console.error("Login error:", error);
          return of(null);
        })
      ).subscribe();
    } else {
      this.errorMessage = 'Please fill out the form correctly.';
    }
  }

}
