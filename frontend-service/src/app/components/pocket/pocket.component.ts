import {Component, Input, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {PocketService} from "../../services/pocket.service";
import {User} from "../../model/User";
import {Pocket} from "../../model/Pocket";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {PocketAddComponent} from "./add/pocket-add.component";

@Component({
  selector: 'app-pocket',
  templateUrl: './pocket.component.html',
  styleUrls: ['./pocket.component.css']
})
export class PocketComponent implements OnInit {

  @Input() user: User | undefined;

  pockets: Pocket[]=[];

  selectedPocket: Pocket | undefined;

  constructor(private oauthService: OAuthService, private pocketService: PocketService,private snackBar: MatSnackBar,
              public pocketAddDialog: MatDialog) {
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
    this.pocketService.getAllPocketsForUser().subscribe({
      next: (data) => {
        this.pockets = data;
      },
      error: () => {
        console.log('Error happened when fetching pockets');
      }
    });
  }

  addPocket(): void{
    this.pocketAddDialog.open(PocketAddComponent);
    this.pocketAddDialog.afterAllClosed.subscribe(() => {
      this.snackBar.open('Transaction created', '', {
        duration: 5000,
        panelClass: ['mat-toolbar', 'mat-primary']
      });
      this.getPockets();
    });
  }

  click(pocket: Pocket){
    this.selectedPocket=pocket;
  }

}
