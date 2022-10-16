import {Component, Input, OnInit} from '@angular/core';
import {Pocket} from "../../model/Pocket";
import {Transaction} from "../../model/Transaction";
import {OAuthService} from "angular-oauth2-oidc";
import {TransactionService} from "../../services/transaction.service";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  @Input() pocket: Pocket | undefined;

  transactions: Transaction[] | undefined;

  constructor(private oauthService: OAuthService, private transactionService: TransactionService) {
  }

  ngOnInit(): void {
    this.transactionService.setAuthHeader(this.oauthService.authorizationHeader());
  }

  ngOnChanges(): void {
    if(this.pocket){
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

}
