import {Component, Input, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {PocketService} from "../../services/pocket.service";
import {User} from "../../model/User";
import {Pocket} from "../../model/Pocket";

@Component({
  selector: 'app-pocket',
  templateUrl: './pocket.component.html',
  styleUrls: ['./pocket.component.css']
})
export class PocketComponent implements OnInit {

  @Input() user: User | undefined;

  pockets: Pocket[]=[];

  selectedPocket: Pocket | undefined;

  constructor(private oauthService: OAuthService, private pocketService: PocketService) {
  }

  ngOnInit(): void {
    this.pocketService.setAuthHeader(this.oauthService.authorizationHeader());
  }

  ngOnChanges():void{
    if(this.user){
      this.getPockets();
    }
  }

  getPockets(): void {
    let userId = this.user?(this.user.id?this.user.id:''):'';
    this.pocketService.getAllPocketsForUser(userId).subscribe(
      data => {
        this.pockets = data;
      },
      error => {
        console.log('Error happend on pockets fetching');
      }
    );
  }

  click(pocket: Pocket){
    this.selectedPocket=pocket;
  }

}
