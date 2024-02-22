import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {


  public user!: User ;
  private userId=this.user?.id;
  topics$: Observable<Topic[]> = this.userService.getTopics(this.userId?.toString());


  constructor(private fb: FormBuilder,private authService: AuthService,private router: Router,
     private sessionService: SessionService, private userService: UserService) { }

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    name: ['', [Validators.required, Validators.name]],
  });
  public ngOnInit(): void {
    this.authService.me().subscribe(
      (user: User) => this.user = user
    )
  }
  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate([''])
  }
  public back() {
    window.history.back();
  }
  submit() {
    throw new Error('Method not implemented.');
    }
}
