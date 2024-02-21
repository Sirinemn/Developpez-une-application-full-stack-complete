import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {
  public user: User | undefined;

  constructor(
    private router: Router,
    private sessionService: SessionService,
    private matSnackBar: MatSnackBar,
    private userService: UserService
  ) {}

  public ngOnInit(): void {
    this.userService
      .getById(this.sessionService.sessionInformation!.id.toString())
      .subscribe((user: User) => (this.user = user));
  }
  public back(): void {
    window.history.back();
  }
}
