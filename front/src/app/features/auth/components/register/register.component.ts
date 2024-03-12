import { Component, OnDestroy, OnInit } from '@angular/core';
import { RegisterRequest } from '../../interfaces/register-request';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnDestroy{
  public onError = false;
  private httpSubscription!: Subscription;
  public errorMessage: string = "";

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],  
    name: [  '', [Validators.required, Validators.min(4), Validators.max(20)], ],
    password: [ '', [Validators.required, Validators.min(4), Validators.max(40)],],
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.httpSubscription = this.authService.register(registerRequest).subscribe(
      () => { this.router.navigate(['login'])
    }, (error: HttpErrorResponse )=> {this.onError = true;
      if(error.status == 400) this.errorMessage = " email or password already exist please try again"
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
