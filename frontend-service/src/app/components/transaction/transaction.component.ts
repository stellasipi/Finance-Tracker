import {Component, Input, OnInit} from '@angular/core';
import {Pocket} from "../../model/Pocket";
import {Transaction} from "../../model/Transaction";
import {OAuthService} from "angular-oauth2-oidc";
import {TransactionService} from "../../services/transaction.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {TransactionEditComponent} from "./edit/transaction-edit.component";
import {TransactionAddComponent} from "./add/transaction-add.component";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  @Input() pocket: Pocket | undefined;

  public pocketName: string = '';

  transactions: Transaction[] | undefined;

  constructor(private oauthService: OAuthService, private transactionService: TransactionService,
              private snackBar: MatSnackBar, public transactionEditDialog: MatDialog, public transactionAddDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.transactionService.setAuthHeader(this.oauthService.authorizationHeader());
  }

  ngOnChanges(): void {
    if(this.pocket){
      this.pocketName = this.pocket.name;
      this.getTransactions();
    }
  }

  getTransactions():void{
    let pocketId=this.pocket?(this.pocket.id?this.pocket.id:''):'';
    this.transactionService.getAllTransactionsForPocket(pocketId).subscribe({
      next: (data)=>{
        this.transactions=data;
      },
      error: () => {
        console.log('Error happened when fetching transactions');
      }
    });
  }

  public addTransaction(): void {
    this.transactionAddDialog.open(TransactionAddComponent);
  }

  public editTransaction(transaction: Transaction): void {
    this.transactionEditDialog.open(TransactionEditComponent, {
      data: {
        transaction: transaction
      }
    });
  }

  public deleteTransaction(transactionId: string): void {
    this.transactionService.deleteTransaction(transactionId).subscribe({
      next: () => {
        this.snackBar.open('Transaction deleted', '', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
        this.getTransactions();
      },
      error: () => {
        console.log('Error happened when deleting transactions');
      }
    });
  }
}
