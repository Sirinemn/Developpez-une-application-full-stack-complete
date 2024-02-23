import { Component } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';
import { ArticleApiService } from '../../services/article-api.service';
import { User } from 'src/app/interfaces/user.interface';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  constructor(private sessionService:SessionService,
    private articleApiService: ArticleApiService
    ) { }

    public articles$ = this.articleApiService.allByUser(JSON.parse(localStorage.getItem('userID')!));

    get user(): User | undefined {
      return this.sessionService.user;
    }


}
