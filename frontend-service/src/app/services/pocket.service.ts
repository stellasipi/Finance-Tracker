import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pocket} from "../model/Pocket";
import {pocketServiceUrl, pocketServiceUrlDocker} from "../constans";

@Injectable({
  providedIn: 'root'
})
export class PocketService {
  private path = pocketServiceUrlDocker + '/pocket';

  private authorizationHeader: { headers: HttpHeaders; } | undefined;

  constructor(private http: HttpClient) {
  }

  setAuthHeader(authHeader: string): void {
    this.authorizationHeader = {
      headers: new HttpHeaders()
        .set('Authorization', authHeader)
    }
  }

  getAllPocketsForUser(userId: string): Observable<Pocket[]> {
    return this.http.get<Pocket[]>(this.path + '/all', this.authorizationHeader);
  }
}
