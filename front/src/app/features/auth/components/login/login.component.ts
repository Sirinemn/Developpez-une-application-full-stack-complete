import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/login-request';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { User } from 'src/app/interfaces/user.interface';
import { Subscription, throwError } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnDestroy{
  public hide = true;
  public onError = false;
  private httpSubscription!: Subscription;
  public errorMessage: string = "";


  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.min(3)]],
  });

  constructor(private authService: AuthService, 
    private fb: FormBuilder, 
    private router: Router,
    private sessionService: SessionService) { }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.httpSubscription = this.authService.login(loginRequest).subscribe(
      (response: SessionInformation) => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('userID', response.id.toString());
        this.authService.getUserById(response.id.toString()).subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/articles'])
        });
        this.router.navigate(['/articles'])
      },
      (error:HttpErrorResponse) =>{
        this.onError=true;
        if(error.status==403) this.errorMessage= "Please verify your email or you password";
      }
    );
  }
  public back() {
    window.history.back();
  }
  ngOnDestroy() {
    this.httpSubscription.unsubscribe();
}
}
