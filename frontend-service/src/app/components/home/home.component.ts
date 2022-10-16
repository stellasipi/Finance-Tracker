import {Component, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {UserService} from "../../services/user.service";
import {User} from "../../model/User";
import {MatDialog} from "@angular/material/dialog";
import {UserComponent} from "../user/user.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public loggedInUser: User | undefined;

  constructor(private oauthService: OAuthService, private userService: UserService, public userInfoDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.setAuthHeader(this.oauthService.authorizationHeader());
    this.setLoggedInUser();
  }

  private setLoggedInUser(): void {
    this.userService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user
      },
      error: () => {
        console.log('Error happened when fetching user info');
      }
    });
  }

  public loadUserInfo(): void {
    this.userInfoDialog.open(UserComponent);
  }
}
