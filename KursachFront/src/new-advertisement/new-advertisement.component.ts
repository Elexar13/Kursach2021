import { Component, OnInit } from '@angular/core';
import {Advertisement} from '../entity/advertisement';
import {HttpClient} from '@angular/common/http';
import {DataGetterService} from '../app/data-getter.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-advertisement',
  templateUrl: './new-advertisement.component.html',
  styleUrls: ['./new-advertisement.component.css']
})
export class NewAdvertisementComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: any;
  public currentUserId: number;
  public advertisement: Advertisement = new Advertisement();
  public advertisementId = 0;
  public editMode = 'off';
  public filePath = 'assets/img/';

  constructor(private http: HttpClient,
              private dataGetter: DataGetterService,
              private router: Router)
  {
    this.currentUserId = dataGetter.getCurrentUserId();
    dataGetter.getUserById(this.currentUserId).subscribe(user => this.user = user);
    this.advertisement.userId = this.currentUserId;
    this.editMode = 'off';
  }

  ngOnInit(): void {
    this.editMode = 'off';
    if (!this.dataGetter.getAdvertisementEditMode) {
      this.editMode = 'off';
    } else {
      console.log('EDIT MODE');
      this.editMode = 'on';
      this.advertisement = this.dataGetter.getAdvertisement();
    }
    this.dataGetter.getCurrentUser().subscribe(user => {
      console.log(user);
      if (user == null || !user.userId){
        this.router.navigate(['/start']);
      } else {
        this.user = user;
      }
    });
  }

  goToFavorites() {
    this.dataGetter.setCurrentUserId(this.currentUserId);
    this.dataGetter.goToFavorites();
  }

  goToMyAdvertisements() {
    this.dataGetter.setCurrentUserId(this.user.userId);
    this.dataGetter.goToMyAdvertisements();
  }

  public logOut(){
    this.dataGetter.logOut();
  }

  onFileSelected(event: any) {
    this.filePath += event.target.files[0].name;
  }

  addAdvertisement(advertisement: Advertisement) {
    this.advertisement.filePath = this.filePath;
    this.http.post<any>(this.baseUrl + '/advertisement?actionName=addAdvertisement', advertisement)
      .subscribe(advertisementId => {
        this.advertisementId = advertisementId;
        console.log(advertisementId);
        this.goToMyAdvertisements();
      });
  }

  updateAdvertisement(advertisement: Advertisement) {
    this.advertisement.filePath = this.filePath;
    this.http.post<any>(this.baseUrl + '/advertisement?actionName=updateAdvertisement', advertisement)
      .subscribe(advertisementId => {
        this.advertisementId = advertisementId;
        console.log(advertisementId);
        this.goToMyAdvertisements();
      });
  }
}
