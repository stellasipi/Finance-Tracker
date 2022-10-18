import {Component, Input, OnInit} from '@angular/core';
import {Transaction} from "../../../model/Transaction";
import {OAuthService} from "angular-oauth2-oidc";
import {TransactionService} from "../../../services/transaction.service";

@Component({
  selector: 'app-transaction-add',
  templateUrl: './transaction-add.component.html',
  styleUrls: ['./transaction-add.component.css']
})
export class TransactionAddComponent implements OnInit {

  @Input() transaction: Transaction | undefined;

  constructor(private oauthService: OAuthService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.transactionService.setAuthHeader(this.oauthService.authorizationHeader());
  }

}
