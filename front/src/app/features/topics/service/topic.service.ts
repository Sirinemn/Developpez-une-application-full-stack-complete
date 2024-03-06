import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TopicsResponse } from '../interfaces/topicsResponse.interface';
import { MessageResponse } from 'src/app/interfaces/api/messageResponse.interface';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  
  private pathService = 'http://localhost:8081/api/topics';

  constructor(private httpClient: HttpClient) {}

  public getAllTopics(): Observable<TopicsResponse> {
    return this.httpClient.get<TopicsResponse>(`${this.pathService}`);
  }
  public create(topic: Topic): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.pathService}`, topic)
  }
}
