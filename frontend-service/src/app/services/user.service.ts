import {Injectable} from '@angular/core';
import {User} from "../model/User";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {fetchRegisterUrl} from "../constans";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private path = '/user';

  constructor(private http: HttpClient) {
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(fetchRegisterUrl + this.path + '/register', user);
  }
}
