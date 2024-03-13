import { Component, OnDestroy, OnInit } from '@angular/core';
import { TopicService } from '../../service/topic.service';
import { UserService } from 'src/app/services/user.service';
import { Subscribtion } from 'src/app/interfaces/api/subscribtion.interface';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MessageResponse } from 'src/app/interfaces/api/messageResponse.interface';
import { Topic } from '../../interfaces/topic.interface';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit, OnDestroy {
  private userId!: string;
  private httpSubscriptions: Subscription[] = [];
  private topicIdList: number[] = [];
  public topics: Topic[] = [];
  constructor(
    private topicService: TopicService,
    private matSnackBar: MatSnackBar,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.userId = JSON.parse(localStorage.getItem('userID')!);
    this.getTableTopics();

  }

  subscribe(id: number): void {
    const subscribtion: Subscribtion = {
      userId: this.userId,
      topicId: id.toString(),
    };
    this.httpSubscriptions.push(
      this.userService
        .subscribe(subscribtion)
        .subscribe((commentResponse: MessageResponse) => {
          this.matSnackBar.open(commentResponse.message, 'Close', {
            duration: 3000,
          });
          this.getTableTopics();
        })
    );
  }
  public getTableTopics():Topic[]{
    this.httpSubscriptions.push(
      this.userService.getTopics(this.userId).subscribe((list) => {
        this.topicIdList = list.map((topic) => topic.id!);
        this.httpSubscriptions.push(
          this.topicService.getAllTopics().subscribe((result) => {
            this.topics = result.topics.filter(
              (topic) => !this.topicIdList.includes(topic.id!)
            );
          })
        );
      })
    );
    return this.topics ;
  }
  ngOnDestroy(): void {
    this.httpSubscriptions.forEach(subscribtion=> subscribtion.unsubscribe());
  }
}
