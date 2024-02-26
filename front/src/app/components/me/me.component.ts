import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { Subscribtion } from 'src/app/interfaces/api/subscribtion.interface';
import { MessageResponse } from 'src/app/interfaces/api/messageResponse.interface';
@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {

  public user!: User ;
  private userId= localStorage.getItem('userID')!;
  topics$: Observable<Topic[]> = this.userService.getTopics(this.userId?.toString());
  meForm!: FormGroup<any>;


  constructor(private fb: FormBuilder,private authService: AuthService,private router: Router,private matSnackBar: MatSnackBar,
     private sessionService: SessionService, private userService: UserService) { }


  public ngOnInit(): void {
    this.authService.me().subscribe(
      (user: User) => this.user = user
    )
    this.initForm(this.user);
  }
  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate([''])
  }
  public back() {
    window.history.back();
  }
  public submit(): void {
    const user = this.meForm?.value as User;
    this.userService.update(this.userId, this.user).subscribe((messageResponse: MessageResponse) =>
    {this.matSnackBar.open(messageResponse.message, "Close", { duration: 3000});
    this.router.navigate(['articles']);}
    )
    }
  public unsubscribe(topicId: number) {
      const subscribtion = {
        userId: this.userId,
        topicId: topicId.toString()
      } as Subscribtion;
      this.userService.unsbscribe(subscribtion).subscribe((commentResponse: MessageResponse)=>
      {this.matSnackBar.open(commentResponse.message, "Close", { duration: 3000});
    });
      }
      private initForm(user?: User): void {
        this.meForm = this.fb.group({
          name: [{value: user?.name},
            [Validators.required]
          ],
          email: [
            {value: user?.email},
            [
              Validators.required,
            ]
          ],
        });
      }
}
