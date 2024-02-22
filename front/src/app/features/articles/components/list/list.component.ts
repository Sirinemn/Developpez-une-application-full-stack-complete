import { Component } from '@angular/core';
import { Article } from '../../interfaces/article.interface';
import { Observable } from 'rxjs';
import { SessionService } from 'src/app/services/session.service';
import { ArticleApiService } from '../../services/article-api.service';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { User } from 'src/app/interfaces/user.interface';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {
  private userId!: string;

  constructor(private sessionService:SessionService,
    private articleApiService: ArticleApiService
    ) { this.userId=this.sessionService.user!.id.toString()}

    public articles$: Observable<Article[]> = this.articleApiService.allByUser(this.userId);

    get user(): User | undefined {
      return this.sessionService.user;
    }


}
