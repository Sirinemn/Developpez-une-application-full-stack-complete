import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';
import { MessageResponse } from '../interfaces/api/messageResponse.interface';
import { Subscribtion } from '../interfaces/api/subscribtion.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private pathService = 'http://localhost:8080/api/user';
  constructor(private httpClient: HttpClient) {}

  public getTopics(id: string): Observable<Topic[]>{
    return this.httpClient.get<Topic[]>(`${this.pathService}/${id}/topics`);

  }
  public unsbscribe(subscribtion: Subscribtion): Observable<MessageResponse>{
    return this.httpClient.post<MessageResponse>(`${this.pathService}/${subscribtion.userId}/unsubscribe/${subscribtion.topicId}`, subscribtion)
  }
  public subscribe(subscribtion: Subscribtion): Observable<MessageResponse>{
    return this.httpClient.post<MessageResponse>(`${this.pathService}/${subscribtion.userId}/subscribe/${subscribtion.topicId}`, subscribtion)
  }
  public update(id: string, form: FormData): Observable<MessageResponse>{
    return this.httpClient.put<MessageResponse>(`${this.pathService}/update/${id}`, form)

  }

}
