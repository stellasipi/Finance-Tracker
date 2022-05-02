import {Injectable} from '@angular/core';
import {User} from "../model/User";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = 'http://localhost:8080/user';

  constructor(private http: HttpClient) {
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>('http://localhost:8081/user/register', user);
  }
}
