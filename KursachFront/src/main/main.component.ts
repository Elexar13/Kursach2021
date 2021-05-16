import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Advertisement} from '../entity/advertisement';
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
  public user: User = new User();
  public advertisements: any;
  public favoriteAdvertisements: any;
  public filterAdvertisement: Advertisement = new Advertisement();
  constructor(private dataGetter: DataGetterService,
              private http: HttpClient,
              private router: Router)
  {}

  ngOnInit(): void {
    this.dataGetter.getCurrentUser().subscribe(user => {
      console.log(user);
      if (user == null || !user.userId){
        this.router.navigate(['/start']);
      } else {
        this.user = user;
      }
    });
    this.getFilteredAdvertisement();
  }

  public getFilteredAdvertisement() {
    this.http.post<any>(this.baseUrl + '/advertisement?actionName=getFilteredAdvertisement', this.filterAdvertisement)
      .subscribe((advList: any) => {
        this.advertisements = advList;
      });
  }

  public logOut(){
    this.dataGetter.logOut();
  }

  public getFavoritesForCurrentUser(){
    return this.http.get<any>(this.baseUrl + '/user?actionName=getFavoritesForCurrentUser');
  }

  goToAdvertisementView(advertisement: Advertisement) {
    this.dataGetter.setUserId(advertisement.userId);
    this.dataGetter.setCurrentUserId(this.user.userId);
    this.dataGetter.setAdvertisement(advertisement);
    this.router.navigate(['/advertisement']);
  }

  goToFavorites() {
    this.dataGetter.setCurrentUserId(this.user.userId);
    this.dataGetter.goToFavorites();
  }
}
