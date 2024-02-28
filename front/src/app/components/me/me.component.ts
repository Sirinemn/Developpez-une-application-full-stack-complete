import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/features/topics/interfaces/topic.interface';
import { MessageResponse } from 'src/app/interfaces/api/messageResponse.interface';
@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {
  public user!: User;
  public userId!: string;
  public topics$!: Observable<Topic[]> ;
  meForm!: FormGroup<any>;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private matSnackBar: MatSnackBar,
    private sessionService: SessionService,
    private userService: UserService
  ) {}

  public ngOnInit() {
    this.userId =  JSON.parse(localStorage.getItem('userID')!);
    this.authService.getUserById(this.userId).subscribe((user: User) => (this.user = user));
    this.topics$= this.userService.getTopics(
      this.userId?.toString()
    );
    console.log(this.user);
    this.initForm(this.user);
  }
  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);
  }
  public back() {
    window.history.back();
  }
  public submit(): void {
    const user = this.meForm?.value as User;
    this.userService
      .update(this.userId, this.user)
      .subscribe((messageResponse: MessageResponse) => {
        this.matSnackBar.open(messageResponse.message, 'Close', {
          duration: 3000,
        });
        this.router.navigate(['me']);
      });
  }
  public unsubscribe(topicId: number) {
    this.userService
      .unsbscribe(this.userId,topicId.toString())
      .subscribe();
  }
  private initForm(user?: User): void {
    this.meForm = this.fb.group({
      name: [user ? user.name : '', [Validators.required]],
      email: [user ? user.email : '', [Validators.required]],
    });
    this.meForm.patchValue({
      name:  user?.name,
      email: user?.email
  
      })
  }

}
