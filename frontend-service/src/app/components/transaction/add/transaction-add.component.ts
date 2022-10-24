import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {Transaction} from "../../../model/Transaction";
import {OAuthService} from "angular-oauth2-oidc";
import {TransactionService} from "../../../services/transaction.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {User} from "../../../model/User";
import {Pocket} from "../../../model/Pocket";

@Component({
  selector: 'app-transaction-add',
  templateUrl: './transaction-add.component.html',
  styleUrls: ['./transaction-add.component.css']
})
export class TransactionAddComponent implements OnInit {

  public newTransaction: Transaction;

  private user: User;

  private pocket: Pocket;

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

  constructor(private oauthService: OAuthService, private transactionService: TransactionService, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.newTransaction = this.copyTransaction(this.emptyTransaction);
    this.user = this.data.user;
    this.pocket = this.data.pocket;
  }

  ngOnInit(): void {
    this.transactionService.setAuthHeader(this.oauthService.authorizationHeader());
  }

  public onSubmit(){
    if (this.user.id != null) {
      this.newTransaction.userId = this.user.id;
    }
    if (this.pocket.id != null) {
      this.newTransaction.pocketId = this.pocket.id;
    }
    this.transactionService.createTransaction(this.newTransaction).subscribe({
      next: () => {
        this.newTransaction = this.copyTransaction(this.emptyTransaction);
      },
      error: () => {
        console.log("Some error happened");
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
