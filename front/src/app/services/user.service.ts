import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private pathService = 'http://localhost:8080/api/user';
  constructor(private httpClient: HttpClient) {}

  public getTopics(id: string): Observable<Topic[]>{
    return this.httpClient.get<Topic[]>(`${this.pathService}/${id}/topics`);

  }

}
