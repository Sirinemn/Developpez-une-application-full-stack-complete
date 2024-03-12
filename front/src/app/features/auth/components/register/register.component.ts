import { Component, OnInit } from '@angular/core';
import { RegisterRequest } from '../../interfaces/register-request';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    
    name: [
      '',
      [Validators.required, Validators.min(3), Validators.max(20)],
    ],
    password: [
      '',
      [Validators.required, Validators.min(3), Validators.max(40)],
    ],
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(
      () => { this.router.navigate(['login'])
    }, error => this.onError = true)
  ;
  }
  public back() {
    window.history.back();
  }
}
