import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DataGetterService} from "../app/data-getter.service";
import {Router} from "@angular/router";
import {Advertisement} from "../entity/advertisement";

@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})
export class AdminViewComponent implements OnInit {
  baseUrl = 'http://localhost:8080/Kursach2021';
  public user: any;
  public currentUserId: number;
  public advertisements: any;

  constructor(private http: HttpClient,
              private dataGetter: DataGetterService,
              private router: Router)
  {
    this.currentUserId = dataGetter.getCurrentUserId();
    dataGetter.getUserById(this.currentUserId).subscribe(user => this.user = user);
    this.getAdminAdvertisements()
      .subscribe(advList => {
        this.advertisements = advList;
      });
  }

  ngOnInit(): void {
    this.dataGetter.getCurrentUser().subscribe(user => {
      console.log(user);
      if (user == null || !user.userId){
        this.router.navigate(['/start']);
      } else {
        this.user = user;
      }
    });
  }

  public goToAdvertisementView(advertisement: Advertisement){
    this.dataGetter.setUserId(advertisement.userId);
    this.dataGetter.setCurrentUserId(this.currentUserId);
    this.dataGetter.setAdvertisement(advertisement);
    this.dataGetter.goToAdvertisementView();
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

  goToEditAdvertisement(advertisement: Advertisement) {
    this.dataGetter.setAdvertisement(advertisement);
    this.dataGetter.setAdvertisementEditMode('edit');
    this.dataGetter.goToAddNewAdvertisement();
  }

  deleteAdvertisement(advertisementId: number) {
    return this.http.post<any>(this.baseUrl + '/advertisement?actionName=deleteAdvertisement', advertisementId)
      .toPromise();
  }

  private getAdminAdvertisements() {
    return this.http.get<any>(this.baseUrl + '/advertisement?actionName=getAdminAdvertisements');
  }

  approvePublication(advertisementId: any) {
    return this.http.post<any>(this.baseUrl + '/advertisement?actionName=approvePublication', advertisementId)
      .subscribe(() => {
        window.location.reload();
      });
  }

  rejectPublication(advertisementId: any) {
    return this.http.post<any>(this.baseUrl + '/advertisement?actionName=rejectPublication', advertisementId)
      .subscribe(() => {
        window.location.reload();
      });
  }
}
