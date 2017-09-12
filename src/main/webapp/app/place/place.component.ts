import { Component, Input, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { PlaceService } from './place.service';
import { Account, LoginModalService, Principal } from '../shared';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'jhi-place',
  templateUrl: './place.component.html',
  styleUrls: [
    'place.scss'
  ]

})

export class PlaceComponent implements OnInit  {
  account: Account;
  modalRef: NgbModalRef;
  location: any;
  ids: number;
  isNavbarCollapsed: boolean;
  
  constructor(
    route: ActivatedRoute,
    private principal: Principal,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private placeService: PlaceService,
   
  ) {
    this.ids = route.snapshot.params['id'];
   this.placeService.findPlace( this.ids).subscribe(res=>{this.location=res;console.log(res)},error=>console.log(error))

  }

  collapseNavbar() {
    this.isNavbarCollapsed = true;
}

ngOnInit() {
  this.principal.identity().then((account) => {
    this.account = account;
    console.log(JSON.stringify(this.account))
  });

  this.registerAuthenticationSuccess();
}

registerAuthenticationSuccess() {
  this.eventManager.subscribe('authenticationSuccess', (message) => {
    this.principal.identity().then((account) => {
      this.account = account;
    });
  });
}


login() {
  this.modalRef = this.loginModalService.open();
}
}
