import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../features/topics/interfaces/topic.interface';
import { MessageResponse } from '../interfaces/api/messageResponse.interface';
import { Subscribtion } from '../interfaces/api/subscribtion.interface';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private pathService = 'http://localhost:8080/api/user';
  constructor(private httpClient: HttpClient) {}

  public getTopics(id: string): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/${id}/topics`);
  }
  public unsbscribe(id: string, topicId: string): Observable<void> {
    return this.httpClient.delete<void>(
      `${this.pathService}/${id}/unsubscribe/${topicId}`
    );
  }
  public subscribe(subscribtion: Subscribtion): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(
      `${this.pathService}/${subscribtion.userId}/subscribe/${subscribtion.topicId}`,
      subscribtion
    );
  }
  public update(form: FormData,id: string): Observable<MessageResponse> {
    return this.httpClient.put<MessageResponse>(
      `${this.pathService}/update/${id}`,form);
  }
}
