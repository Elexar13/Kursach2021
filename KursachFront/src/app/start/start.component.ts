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
  public user: User | null = null;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // this.http.get(this.baseUrl + '/login').subscribe(
    //   data => {
    //         this.user = data;
    //         console.log(this.user);
    //   }
    // );
  }

}
