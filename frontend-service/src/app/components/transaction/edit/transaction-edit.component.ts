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

  constructor(private oauthService: OAuthService, private transactionService: TransactionService,
              @Inject(MAT_DIALOG_DATA) public data: Transaction) { }

  ngOnInit(): void {
    this.transactionService.setAuthHeader(this.oauthService.authorizationHeader());
  }

}
