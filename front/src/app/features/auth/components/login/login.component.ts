import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/login-request';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.min(3)]],
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        debugger;
        this.sessionService.logIn(response);
        this.router.navigate(['/articles']);
      },
      error: (error) => (this.onError = true),
    });
  }
}
