import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent 
//doto: complete missing code..
{
  itemForm : FormGroup;
  formModel:any={role:null,email:'',password:'',username:''}; 
  showMessage:boolean=false; 
  errorMessage : any;
  responseMessage: any; 
    constructor(private fb:FormBuilder, private httpService: HttpService, private router:Router){
      this.itemForm = this.fb.group({
        username : ['',[Validators.required]],
        email : ['',[Validators.required,Validators.email]],
        password : ['',[Validators.required,Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@$%&*?])[A-Za-z\d!@$%&*?]{8,}$/)]],
        role: ['',[Validators.required]]
      })
    }
  
    ngOnInit(){
      this.showMessage = false;
    }
  
  get f(){
   return this.itemForm.controls;
  }
  
  onSubmit() {
    if (this.itemForm.valid) {
      this.httpService.register(this.itemForm.value).subscribe({
        next: (response) => {
          this.itemForm.reset();
          this.showMessage = true;
          this.responseMessage = "Registration successful! Redirecting to login...";

          setTimeout(() => {
            this.showMessage = false;
            this.router.navigate(['login']);
          }, 2000);
        },
        error: (err) => {
          this.showMessage = true;
          this.responseMessage = "Registration failed. Please try again.";
          this.errorMessage = err.error;
          
        }
      });
    } else {
      this.itemForm.markAllAsTouched();
    }
  }
  }
