import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Transaction} from "../model/Transaction";
import {transactionServiceUrl} from "../constans";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  // private path = '/transaction-service/transaction';
  private path = '/transaction';

  private authorizationHeader: { headers: HttpHeaders; } | undefined;

  constructor(private http: HttpClient) {
  }

  setAuthHeader(authHeader: string): void {
    this.authorizationHeader = {
      headers: new HttpHeaders()
        .set('Authorization', authHeader)
    }
  }

  getAllTransactionsForPocket(pocketId: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(transactionServiceUrl + this.path + '/all?pocketId=' + pocketId, this.authorizationHeader);
  }
}
