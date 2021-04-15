import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get(this.baseUrl + '/login').subscribe(
      data => {
            console.log(data);
      }
    );
  }

}
