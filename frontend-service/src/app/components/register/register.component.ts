import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/User";

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

  //createdUser: User;

  constructor(private userService: UserService) {
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

    this.userService.registerUser(newUser).subscribe((user) => {
      console.dir(user);
    });

    this.name = '';
    this.username = '';
    this.email = '';
    this.password = '';
  }
}
