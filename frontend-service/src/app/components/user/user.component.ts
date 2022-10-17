import {Component, Input, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {UserService} from "../../services/user.service";
import {User} from "../../model/User";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public originalUser: User = {
    id: '',
    name: '',
    email: '',
    username: '',
    role: ''
  };

  public user: User = {
    id: '',
    name: '',
    email: '',
    username: '',
    role: ''
  };

  public modifiedUser: User = {
    name: '',
    email: '',
    username: ''
  }

  public modifyPassword = {
    username: '',
    oldPassword: '',
    newPassword: ''
  }

  public oldPassword: string = '';
  public newPassword: string = '';

  public disabled = {
    name: true,
    email: true,
    username: true
  }

  constructor(private oauthService: OAuthService, private userService: UserService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.userService.setAuthHeader(this.oauthService.authorizationHeader());
    this.getUserInfo();
  }

  private getUserInfo(): void {
    this.userService.getLoggedInUser().subscribe({
      next: (user) => {
        this.user = user;
        this.originalUser = user;
      },
      error: () => {
        console.log('Error happened when fetching user info');
      }
    });
  }

  public onEdit(type: string): void {
    if(type==='name'){
      this.disabled.name = false;
    }
    if(type==='email'){
      this.disabled.email = false;
    }
    if(type==='username'){
      this.disabled.username = false;
    }
  }

  public onChange(type: string): void {
    this.setModifiedUser();
    this.modifyUser();

    if(type==='name'){
      this.disabled.name = true;
    }
    if(type==='email'){
      this.disabled.email = true;
    }
    if(type==='username'){
      this.disabled.username = true;
    }
  }

  public onDecline(type: string): void {
    if(type==='name'){
      this.user.name=this.originalUser.name;
      this.disabled.name = true;
    }
    if(type==='email'){
      this.user.email=this.originalUser.email;
      this.disabled.email = true;
    }
    if(type==='username'){
      this.user.username=this.originalUser.username;
      this.disabled.username = true;
    }
  }

  public onChangePassword(){
    this.modifyPassword.username=this.originalUser.username;
    this.modifyPassword.oldPassword=this.oldPassword;
    this.modifyPassword.newPassword=this.newPassword;
    this.userService.modifyPassword(this.modifyPassword).subscribe({
      next: (date) => {
        this.snackBar.open('Password changed', '', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
      },
      error: () => {
        console.log('Error happened');
      }
    });
    this.modifyPassword.oldPassword='';
    this.modifyPassword.newPassword='';
  }

  private setModifiedUser(): void {
    this.modifiedUser.name=this.user.name;
    this.modifiedUser.email=this.user.email;
    this.modifiedUser.username=this.user.username;
  }

  private modifyUser(): void {
    this.userService.modifyUser(this.modifiedUser).subscribe({
      next: (data) => {
        this.originalUser.name=data.name;
        this.originalUser.email=data.email;
        this.originalUser.username=data.username;
        this.user.name=data.name;
        this.user.email=data.email;
        this.user.username=data.username;
      },
      error: () => {
        console.log('Error happened');
      }
    });
  }

}
