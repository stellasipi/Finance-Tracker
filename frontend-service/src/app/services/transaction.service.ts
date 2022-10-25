import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Transaction} from "../model/Transaction";
import {transactionServiceUrl, transactionServiceUrlDocker} from "../constans";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private path = transactionServiceUrlDocker + '/transaction';

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
    return this.http.get<Transaction[]>(this.path + '/all?pocketId=' + pocketId, this.authorizationHeader);
  }

  createTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(this.path, transaction, this.authorizationHeader);
  }

  modifyTransaction(id: string, transaction: Transaction): Observable<Transaction> {
    return this.http.put<Transaction>(this.path + '?transactionId=' + id, transaction, this.authorizationHeader);
  }

  deleteTransaction(transactionId: string): Observable<any> {
    return this.http.delete(this.path + '/delete?id=' + transactionId, this.authorizationHeader);
  }
}
