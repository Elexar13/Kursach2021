import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../entity/user';

@Injectable({
  providedIn: 'root'
})
export class DataGetterService {
  public user: User = new User();

  constructor(private http: HttpClient) { }


  public setUser(user: User){
    this.user = user;
  }

  public getUser(){
    return this.user;
  }
}
