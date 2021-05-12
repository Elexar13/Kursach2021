import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../entity/user';
import {provideRoutes, Router} from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: User = new User();
  public userId = 0;
  public isAdmin = 'N';

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  public loginUser(user: User){
    console.log(user);
    return this.http.post<any>(this.baseUrl + '/user?actionName=getUser', user)
      .subscribe(result => {
        this.isAdmin = result.isAdmin;
        this.userId = result.userId;
        if (result) {
          this.router.navigate(['/main']);
        } else {
          console.log('There are no user');
        }
      });
  }

}
