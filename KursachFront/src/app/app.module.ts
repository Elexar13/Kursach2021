import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StartComponent } from '../start/start.component';
import { RouterModule } from '@angular/router';
import {HttpClientModule} from "@angular/common/http";
import { AlertModule } from 'ngx-bootstrap/alert';
import {FormsModule} from "@angular/forms";
import { RegistrationComponent } from '../registration/registration.component';
import { MainComponent } from '../main/main.component';
import { AdvertisementComponent } from '../advertisement/advertisement.component';
import {Advertisement} from "../entity/advertisement";
import {DataGetterService} from './data-getter.service';
import { FavoritesComponent } from '../favorites/favorites.component';
import { MyAdvertisementsComponent } from '../my-advertisements/my-advertisements.component';
import { NewAdvertisementComponent } from '../new-advertisement/new-advertisement.component';

const routes = [
  {path: 'start', component: StartComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'main', component: MainComponent},
  {path: 'advertisement', component: AdvertisementComponent},
  {path: 'favorites', component: FavoritesComponent},
  {path: 'my-advertisement', component: MyAdvertisementsComponent},
  {path: 'new-advertisement', component: NewAdvertisementComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    RegistrationComponent,
    MainComponent,
    AdvertisementComponent,
    FavoritesComponent,
    MyAdvertisementsComponent,
    NewAdvertisementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [DataGetterService],
  bootstrap: [AppComponent]
})
export class AppModule {

}
