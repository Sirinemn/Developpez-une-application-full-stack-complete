import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TopicsResponse } from '../interfaces/topicsResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  
  private pathService = 'http://localhost:8080/api/topics';

  constructor(private httpClient: HttpClient) {}

  public getAllTopics(): Observable<TopicsResponse> {
    return this.httpClient.get<TopicsResponse>(`${this.pathService}`);
  }
}
