import { Component } from '@angular/core';
import { TopicService } from '../service/topic.service';
import { SessionService } from 'src/app/services/session.service';
import { User } from 'src/app/interfaces/user.interface';
import { Topic } from 'src/app/interfaces/topic.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {
subscribe(arg0: number) {
throw new Error('Method not implemented.');
}
  public userId= JSON.parse(localStorage.getItem('userID')!);

  constructor(private topicService: TopicService, private sessionService: SessionService, private userService: UserService) { }

  /*subscribedTopics$= this.userService.getTopics(this.userId).subscribe(list=> list.map(topic=> this.topicIdList.push(topic.id)));
  public topicIdList: number[]=[];
  public topicFinalList: Topic[]= [];
  public topics$ = this.topicService.getAllTopics().subscribe((result)=> 
    result.topics.filter(topic=> {
        for(let i=0;i++;this.topicIdList.length){
        if(topic.id!=this.topicIdList[i]) this.topicFinalList.push(topic);
        }
    }));*/
    public topics$ = this.topicService.getAllTopics();
  


}
