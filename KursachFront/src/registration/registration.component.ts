import { Component, OnInit } from '@angular/core';
import {User} from "../entity/user";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: User = new User();
  public userId: number = 0;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  public addUser(user: User){
    this.http.post<any>(this.baseUrl + '/user?actionName=addUser', user)
      .subscribe(userId => {
        this.userId = userId;
        this.router.navigate(['/main']);
      });
  }

}
