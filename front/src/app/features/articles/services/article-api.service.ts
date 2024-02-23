import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticlesResponse } from '../interfaces/api/articlesResponse.interface';
import { Article } from '../interfaces/article.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticleApiService {
 
  private pathService = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) { }

  public allByUser(id: string): Observable<ArticlesResponse>{
    return this.httpClient.get<ArticlesResponse>(`${this.pathService}/user/${id}/articles`)

  }
  public detail(id: string): Observable<Article>{
    return this.httpClient.get<Article>(`${this.pathService}/article/${id}`)
  }

}
