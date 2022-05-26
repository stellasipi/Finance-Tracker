import {Injectable} from '@angular/core';
import {User} from "../model/User";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {userServiceUrl} from "../constans";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private path = '/user';

  private authorizationHeader: { headers: HttpHeaders; } | undefined;

  constructor(private http: HttpClient) {
  }

  setAuthHeader(authHeader: string): void {
    this.authorizationHeader = {
      headers: new HttpHeaders()
        .set('Authorization', authHeader)
    }
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(userServiceUrl + this.path + '/register', user);
  }

  getLoggedInUser(): Observable<User> {
    return this.http.get<User>(userServiceUrl + this.path + '/info', this.authorizationHeader);
  }
}
