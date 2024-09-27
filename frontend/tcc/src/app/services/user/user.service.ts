import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SignUpUserResponse } from 'src/app/models/interfaces/user/SignUpUserResponse';
import { SignUpUserRequest } from 'src/app/models/interfaces/user/SignUpUserRequest';
import { environment } from 'src/environments/environment';
import { AuthRequest } from 'src/app/models/interfaces/user/auth/AuthRequest';
import { AuthResponse } from 'src/app/models/interfaces/user/auth/AuthResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private API_URL = environment.API_URL;

  constructor(private http: HttpClient, private cookiService: CookieService) { }

  signupUser(requestDatas: SignUpUserRequest): Observable<SignUpUserResponse> {
    return this.http.post<SignUpUserResponse>(
      `${this.API_URL}/user`,
      requestDatas
    );
  }

  authUser(requestDatas: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/auth`, requestDatas);
  }

  isLoggedIn(): boolean {
    const JWT_TOKEN = this.cookiService.get('USER_INFO');
    return JWT_TOKEN ? true : false;
  }
}
