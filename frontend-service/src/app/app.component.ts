import {AfterViewChecked, ChangeDetectorRef, Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewChecked {
  title: string = 'frontend-service';
  _loggedIn: boolean = false;

  constructor(private readonly changeDetectorRef: ChangeDetectorRef) {
  }

  ngAfterViewChecked(): void {
    this.changeDetectorRef.detectChanges();
  }

  setLoggedIn(value: boolean) {
    this._loggedIn = value;
  }

  isLoggedIn(): boolean {
    return this._loggedIn;
  }

}
