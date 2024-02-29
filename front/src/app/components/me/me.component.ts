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
  meForm: FormGroup= this.fb.group({
    name: ['', [Validators.required]],
    email: ['', [Validators.required]],
  });

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
    this.authService.getUserById(this.userId).subscribe((user: User) => {(this.user = user);
      this.initForm(this.user);});
    this.topics$= this.userService.getTopics(
      this.userId?.toString()
    );    
  }
  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);
  }
  public back() {
    window.history.back();
  }
  public submit(): void {
    const formData = new FormData();
    formData.append('name', this.meForm!.get('name')?.value);
    formData.append('email', this.meForm!.get('email')?.value);
    this.userService
      .update(formData, this.userId)
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
    this.meForm.controls
    ['name'].setValue(user?.name) 
    this.meForm.controls
    ['email'].setValue(user?.email) 
  }

}
