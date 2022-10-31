import {Component, Inject, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {PocketService} from "../../../services/pocket.service";
import {Pocket} from "../../../model/Pocket";
import {User} from "../../../model/User";

@Component({
  selector: 'app-pocket-add',
  templateUrl: './pocket-add.component.html',
  styleUrls: ['./pocket-add.component.css']
})
export class PocketAddComponent implements OnInit {

  public newPocket: Pocket;

  private emptyPocket: Pocket = {
    id: '',
    name: '',
    currency: ''
  };

  constructor(private oauthService: OAuthService, private pocketService: PocketService) {
    this.newPocket = this.copyPocket(this.emptyPocket);
  }

  ngOnInit(): void {
    this.pocketService.setAuthHeader(this.oauthService.authorizationHeader());
  }

  public onSubmit(){
    this.pocketService.createPocket(this.newPocket).subscribe({
      next: () => {
        this.newPocket = this.copyPocket(this.emptyPocket);
      },
      error: () => {
        console.log("Some error happened");
      }
    });
  }

  private copyPocket(pocket: Pocket): Pocket{
    return {
      id: pocket.id,
      name: pocket.name,
      currency: pocket.currency
    }
  }

}
