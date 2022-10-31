import {Injectable} from '@angular/core';
import {User} from "../model/User";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {userServiceUrl} from "../constans";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private path = userServiceUrl + '/user';

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
    return this.http.post<User>(this.path + '/register', user);
  }

  getLoggedInUser(): Observable<User> {
    return this.http.get<User>(this.path + '/info', this.authorizationHeader);
  }

  modifyUser(user: User): Observable<User> {
    return this.http.put<User>(this.path, user, this.authorizationHeader)
  }

  modifyPassword(user: Object): Observable<User> {
    return this.http.put<User>(this.path + '/password', user, this.authorizationHeader)
  }
}
