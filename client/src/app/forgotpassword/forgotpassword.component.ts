import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpService } from '../../services/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.scss']
})
export class ForgotpasswordComponent implements OnInit {
    forgotForm!: FormGroup;
    resetForm!: FormGroup;
    otpSent = false;
  successMessage: any;
  errorMessage: any;
  
    constructor(private fb: FormBuilder, private httpService: HttpService, private router: Router) {}
  
    ngOnInit(): void {
      this.forgotForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]]
      });
  
      this.resetForm = this.fb.group({
        otp: ['', Validators.required],
        newPassword: ['', Validators.required]
      });
    }
  
    sendOtp(): void {
      const email = this.forgotForm.value.email;
      this.httpService.sendOtp(email).subscribe(() => {
        this.otpSent = true;
      });
    }
  
   
resetPassword(): void {
  const email = this.forgotForm.value.email;
  const { otp, newPassword } = this.resetForm.value;

  this.httpService.resetPassword({ email, otp, newPassword }).subscribe({
    next: (res) => {
      this.successMessage = res.message;
  
      // Redirect after short delay
      setTimeout(() => {
        this.router.navigate(['/login']);
      }, 2000);
    },
    error: (err) => {
      this.errorMessage = err.error.message || 'Failed to reset password';
    }
  });
}
}