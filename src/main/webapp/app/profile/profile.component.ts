import { Component, Input, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ProfileService } from './profile.service';
import { Account, LoginModalService, Principal } from '../shared';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'jhi-profile',
  templateUrl: './profile.component.html',
  styleUrls: [
    'profile.scss'
  ]

})

export class ProfileComponent implements OnInit  {
  account: Account;
  modalRef: NgbModalRef;
  user: any;
  ids: number;
  isNavbarCollapsed: boolean;
  
  constructor(
    route: ActivatedRoute,
    private principal: Principal,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private profileService: ProfileService,
   
  ) {
    this.ids = route.snapshot.params['id'];
   this.profileService.findUser(localStorage.getItem('username')).subscribe(res=>{this.user=res;console.log(res)},error=>console.log(error))

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
