import {Component, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {UserService} from "../../services/user.service";
import {User} from "../../model/User";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public loggedInUser: User | undefined;

  constructor(private oauthService: OAuthService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.setAuthHeader(this.oauthService.authorizationHeader());
    this.setLoggedInUser();
  }

  private setLoggedInUser(): void {
    this.userService.getLoggedInUser().subscribe(
      user => {
        this.loggedInUser = user;
      }
    );
  }

}
