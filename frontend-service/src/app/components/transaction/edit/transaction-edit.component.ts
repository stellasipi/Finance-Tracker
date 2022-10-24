import {Component, Inject, Input, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {TransactionService} from "../../../services/transaction.service";
import {Transaction} from "../../../model/Transaction";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-transaction-edit',
  templateUrl: './transaction-edit.component.html',
  styleUrls: ['./transaction-edit.component.css']
})
export class TransactionEditComponent implements OnInit {

  public originalTransaction: Transaction;

  public modifiedTransaction: Transaction;

  private emptyTransaction: Transaction = {
    id: '',
    userId: '',
    proceedType: '',
    paymentType: '',
    amount: 0,
    name: '',
    creatorUsername: '',
    description: '',
    createDate: '',
    pocketId: ''
  };

  constructor(private oauthService: OAuthService, private transactionService: TransactionService,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.originalTransaction = this.copyTransaction(this.emptyTransaction);
    this.modifiedTransaction = this.copyTransaction(this.emptyTransaction);
    this.originalTransaction = this.data.transaction;
  }

  ngOnInit(): void {
    this.transactionService.setAuthHeader(this.oauthService.authorizationHeader());
  }

  public onSubmit(){
    this.modifiedTransaction = this.copyTransaction(this.originalTransaction);
    this.transactionService.modifyTransaction(this.modifiedTransaction.id, this.modifiedTransaction).subscribe({
      next: (data) => {
        this.originalTransaction = this.copyTransaction(data);
        this.modifiedTransaction = this.copyTransaction(this.emptyTransaction);
      },
      error: () => {
        console.log("Some error happened");
        this.modifiedTransaction = this.copyTransaction(this.emptyTransaction);
      }
    });
  }

  private copyTransaction(transaction: Transaction): Transaction{
    return {
      amount: transaction.amount,
      createDate: transaction.createDate,
      creatorUsername: transaction.creatorUsername,
      description: transaction.description,
      id: transaction.id,
      name: transaction.name,
      paymentType: transaction.paymentType,
      proceedType: transaction.proceedType,
      userId: transaction.userId,
      pocketId: transaction.pocketId
    }
  }
}
