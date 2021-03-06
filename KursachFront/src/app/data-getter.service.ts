import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../entity/user';
import {Router} from '@angular/router';
import {Advertisement} from '../entity/advertisement';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataGetterService {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: any;
  public advertisement: any;
  private userId = 0;
  private currentUserId: any;
  public advertisementEditMode = null;

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

  public getUserById(userId: number){
    return this.http.get<any>(this.baseUrl + '/user?actionName=getUserById&userId=' + userId);
  }

  public getFilteredAdvertisement(filterAdvertisement: any) {
    return this.http.post<any>(this.baseUrl + '/advertisement?actionName=getFilteredAdvertisement', filterAdvertisement);
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

  getAdvertisementEditMode(){
    return this.advertisementEditMode;
  }

  setAdvertisementEditMode(mode: any){
    this.advertisementEditMode = mode;
  }

  goToAdvertisementView() {
    this.router.navigate(['/advertisement']);
  }

  goToFavorites() {
    this.router.navigate(['/favorites']);
  }

  goToMyAdvertisements() {
    this.router.navigate(['/my-advertisement']);
  }

  goToAddNewAdvertisement() {
    this.router.navigate(['/new-advertisement']);
  }
}
