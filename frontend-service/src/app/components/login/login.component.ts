import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {authCodeFlowConfig} from "../../oauth2.config";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output() loggedInEvent = new EventEmitter<boolean>();

  constructor(public oauthService: OAuthService) {
    this.oauthService.configure(authCodeFlowConfig);
    this.oauthService.tryLogin();
  }

  ngOnInit(): void {
  }

  login(): void {
    console.log("login button clicked");
    this.oauthService.initCodeFlow();
  }

  logout(): void {
    this.oauthService.logOut();
  }

  get loggedIn() {
    let accessToken: any = this.oauthService.getAccessToken();
    this.loggedInEvent.emit(!!accessToken);
    return accessToken ? accessToken : null;
  }
}
