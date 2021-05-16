import {Component, Input, OnInit} from '@angular/core';
import {User} from '../entity/user';
import {HttpClient} from '@angular/common/http';
import {DataGetterService} from '../app/data-getter.service';
import {Advertisement} from '../entity/advertisement';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public currentUser: any;
  public currentUserId: any;
  public user: User = new User();
  public userId: number;
  public advertisement: Advertisement = new Advertisement();

  constructor(public http: HttpClient, private dataGetter: DataGetterService)
  {
    dataGetter.getCurrentUser().subscribe(user => {
      this.currentUser = user;
    });
    this.currentUserId = this.dataGetter.getCurrentUserId();
    this.userId = this.dataGetter.getUserId();
    this.advertisement = this.dataGetter.getAdvertisement();
    this.getUserById(this.userId);
    this.getFavorByIdAndAdId(this.currentUserId, this.advertisement.adId);
    console.log(this.currentUserId);
    console.log(this.advertisement);
  }

  ngOnInit(): void {

  }

  public logOut(){
    this.dataGetter.logOut();
  }

  public getUserById(userId: number){
    return this.dataGetter.getUserById(userId)
      .subscribe(user => {
        this.user = user;
      });
  }

  public getFavorByIdAndAdId(userId: number, adId: any){
    return this.http.get<any>(this.baseUrl + '/advertisement?actionName=getFavorByIdAndAdId&userId=' + userId + '&adId=' + adId)
      .subscribe(res => {
        console.log('search for userId = ' + userId + ' and adId = ' + adId + 'res:');
        console.log(res);
        if (res != null){
          this.advertisement.isFavorite = 'true';
        } else {
          this.advertisement.isFavorite = null;
        }
      });
  }

  public addAdvertisementToFavorite(advertisementId: any){
    this.advertisement.isFavorite = 'true';
    return this.http.post<any>(this.baseUrl + '/advertisement?actionName=addAdvertisementToFavorite&userId=', advertisementId)
      .subscribe(advertisementId => {
      if (advertisementId){
        console.log("Додавання успішне");
      } else {
        console.log("Додавання не успішне");
      }
    });
  }

  public removeFromFavoritesForCurrentUser(advertisementId: any){
    this.advertisement.isFavorite = null;
    return this.http.post<any>(this.baseUrl + '/advertisement?actionName=removeFromFavoritesForCurrentUser&userId=', advertisementId)
      .subscribe(advertisementId => {
        if (advertisementId){
          console.log("Видалення успішне");
        } else {
          console.log("Видалення не успішне");
        }
      });
  }

  goToFavorites() {
    this.dataGetter.setCurrentUserId(this.user.userId);
    this.dataGetter.goToFavorites();
  }

}
