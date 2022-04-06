import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string = 'frontend-service';

  constructor() {
    this.configureOauth2();
  }

  configureOauth2() {
  }

  login(): void {
    console.log("login button");
  }

  logout(): void {
  }

  get token() {
    //let claims: any = this.oauthService.getIdentityClaims();
    let claims: any = null;
    return claims ? claims : null;
  }
}
