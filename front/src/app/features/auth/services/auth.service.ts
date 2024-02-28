import { Injectable } from '@angular/core';
import { RegisterRequest } from '../interfaces/register-request';
import { LoginRequest } from '../interfaces/login-request';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { User } from 'src/app/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private pathService = 'http://localhost:8080/api';


  constructor(private httpClient: HttpClient) { }
  public register(registerRequest: RegisterRequest): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${this.pathService}/login`, loginRequest);
  }
  public me(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }
  public getUserById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/user/${id}`);
  }
}
