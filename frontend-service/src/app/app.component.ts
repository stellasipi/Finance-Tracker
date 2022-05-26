import {Component, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string = 'frontend-service';

  constructor(private oauthService: OAuthService) {
  }

  ngOnInit() {

  }

  get loggedIn() {
    return this.oauthService.hasValidAccessToken();
  }

}
