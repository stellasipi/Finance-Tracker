import {Component} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {authCodeFlowConfig} from "../../oauth2.config";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  constructor(private oauthService: OAuthService, private cookieService: CookieService) {
    this.oauthService.configure(authCodeFlowConfig);
    this.oauthService.tryLogin();
  }

  login(): void {
    this.oauthService.initCodeFlow();
  }

  logout(): void {
    this.oauthService.logOut(true);
    this.cookieService.delete('JSESSIONID'); // TODO fix it
  }

  get loggedIn() {
    return this.oauthService.hasValidAccessToken();
  }
}
