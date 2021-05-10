import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../entity/user';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: User = new User();
  public userId: number = 0;
  public isAdmin: string = 'N';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  public getUser(user: User){
    console.log(user)
    return this.http.post<any>(this.baseUrl + '/login', user)
      .subscribe(result =>{
        console.log(result);
        this.isAdmin = result.isAdmin;
        this.userId = result.userId;
      });
  }

}
