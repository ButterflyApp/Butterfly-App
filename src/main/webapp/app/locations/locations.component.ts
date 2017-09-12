import { Component, Input, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { LocationsService } from './locations.service';
import { Account, LoginModalService, Principal } from '../shared';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'jhi-locations',
  templateUrl: './locations.component.html',
  styleUrls: [
    'locations.scss'
  ]

})

export class LocationsComponent {
  account: Account;
  modalRef: NgbModalRef;
  locations: any;
  id: number;

  
  constructor(
    route: ActivatedRoute,
    private principal: Principal,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private locationsService: LocationsService,
   
  ) {
    this.id = route.snapshot.params['lid'];
    this.locationsService.findLocations( this.id).subscribe(res=>{this.locations=res;console.log(res)},error=>console.log(error))
 
  
  }



  login() {
    this.modalRef = this.loginModalService.open();
  }
}
