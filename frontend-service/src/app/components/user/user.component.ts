import {Component, Input, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {UserService} from "../../services/user.service";
import {User} from "../../model/User";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public user: User | undefined;

  constructor(private oauthService: OAuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.setAuthHeader(this.oauthService.authorizationHeader());
    this.getUserInfo();
  }

  private getUserInfo(): void {
    this.userService.getLoggedInUser().subscribe({
      next: (user) => {
        this.user = user
      },
      error: () => {
        console.log('Error happened when fetching user info');
      }
    });
  }

  public onSubmit(): void {

  }

}
