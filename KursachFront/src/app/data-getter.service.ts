import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../entity/user';
import {Router} from '@angular/router';
import {Advertisement} from '../entity/advertisement';

@Injectable({
  providedIn: 'root'
})
export class DataGetterService {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: any;
  public advertisement: any;
  private userId = 0;
  private currentUserId: any;

  constructor(private http: HttpClient, public router: Router) { }


  public setUser(user: User){
    this.user = user;
  }

  public getUser(){
    return this.user;
  }

  public setUserId(userId: number){
    this.userId = userId;
  }

  public getUserId(){
    return this.userId;
  }

  logOut() {
    return this.http.get<any>(this.baseUrl + '/user?actionName=logOut')
      .subscribe(res => {
        this.router.navigate(['/start']);
      });
  }

  setAdvertisement(advertisement: Advertisement) {
    this.advertisement = advertisement;
  }

  getAdvertisement() {
    return this.advertisement;
  }

  public getCurrentUser(){
    return this.http.get<any>(this.baseUrl + '/user?actionName=getCurrentUser');
  }

  setCurrentUserId(currentUserId: any) {
    this.currentUserId = currentUserId;
  }

  getCurrentUserId() {
    return this.currentUserId;
  }
}
