import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Advertisement} from '../entity/advertisement';
import {newArray} from '@angular/compiler/src/util';
import {DataGetterService} from '../app/data-getter.service';
import {User} from '../entity/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: any;
  public fav: boolean;
  filePath = '../../images/1.jpg';
  public advertisements: any;
  public filterAdvertisement: Advertisement = new Advertisement();

  constructor(private dataGetter: DataGetterService,
              private http: HttpClient,
              private router: Router) {}

  ngOnInit(): void {
    this.getAllAdvertisment();
    this.filterAdvertisement.city = 'Місто';
    this.user = this.dataGetter.getUser();
    console.log(this.user);
  }

  public getAllAdvertisment() {
    this.http.get<any>(this.baseUrl + '/advertisement?actionName=getAllAdvertisement')
      .subscribe((advList: any) => {
        this.advertisements = advList;
      });
  }

  public getFilteredAdvertisement() {
    console.log(this.filterAdvertisement)
    this.http.post<any>(this.baseUrl + '/advertisement?actionName=getFilteredAdvertisement', this.filterAdvertisement)
      .subscribe((advList: any) => {
        this.advertisements = advList;
      });
  }

}
