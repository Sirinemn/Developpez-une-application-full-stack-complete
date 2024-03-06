import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../service/topic.service';
import { UserService } from 'src/app/services/user.service';
import { Subscribtion } from 'src/app/interfaces/api/subscribtion.interface';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MessageResponse } from 'src/app/interfaces/api/messageResponse.interface';
import { Router } from '@angular/router';
import { Topic } from '../../interfaces/topic.interface';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  private userId!: string;
  private topicIdList: number[] = [];
  public topics: Topic[] = [];
  constructor(
    private topicService: TopicService,
    private matSnackBar: MatSnackBar,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = JSON.parse(localStorage.getItem('userID')!);
    this.userService.getTopics(this.userId).subscribe((list) => {
      this.topicIdList = list.map((topic) => topic.id!);
      this.topicService.getAllTopics().subscribe((result) => {
        this.topics = result.topics.filter(
          (topic) => !this.topicIdList.includes(topic.id!)
        );
      });
    });
  }

  subscribe(id: number): void {
    const subscribtion: Subscribtion = {
      userId: this.userId,
      topicId: id.toString(),
    };
    this.userService
      .subscribe(subscribtion)
      .subscribe((commentResponse: MessageResponse) => {
        this.matSnackBar.open(commentResponse.message, 'Close', {
          duration: 3000,
        });
        this.router.navigate(['topics']);
      });
  }
}
