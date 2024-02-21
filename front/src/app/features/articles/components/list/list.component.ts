import { Component } from '@angular/core';
import { Article } from '../../interfaces/article.interface';
import { Observable } from 'rxjs';
import { SessionService } from 'src/app/services/session.service';
import { ArticleApiService } from '../../services/article-api.service';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {
  public userId!: string;


  constructor(private sessionService:SessionService,
    private articleApiService: ArticleApiService
    ) { this.userId=this.sessionService.sessionInformation!.id.toString()}

    public articles$: Observable<Article[]> = this.articleApiService.allByUser(this.userId);

    get user(): SessionInformation | undefined {
      return this.sessionService.sessionInformation;
    }


}
