import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/User";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  name: string = '';
  username: string = '';
  email: string = '';
  password: string = '';

  constructor(private userService: UserService, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const newUser: User = {
      name: this.name,
      username: this.username,
      email: this.email,
      password: this.password
    };

    this.userService.registerUser(newUser).subscribe({
      next: (data) => {
        this.snackBar.open('Registration succeeded', '', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
        this.resetRegisterForm();
      },
      error: () => {
        this.snackBar.open('Registration failed', '', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-warn']
        });
      }
    });
  }

  resetRegisterForm() {
    this.name = '';
    this.username = '';
    this.email = '';
    this.password = '';
  }
}
