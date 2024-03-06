import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentsResponse } from '../interfaces/api/commentsResponse.interface';
import { CommentResponse } from '../interfaces/api/commentResponse.interface';
import { CommentRequest } from '../interfaces/api/commentRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentsServiceService {

  private pathService = 'http://localhost:8081/api/comment';

  constructor(private httpClient: HttpClient) { }

  public allcomments(id: string): Observable<CommentsResponse>{
    return this.httpClient.get<CommentsResponse>(`${this.pathService}/${id}`)
  }
  public send(comment: CommentRequest): Observable<CommentResponse>{
    return this.httpClient.post<CommentResponse>(`${this.pathService}`, comment);
  }
}
