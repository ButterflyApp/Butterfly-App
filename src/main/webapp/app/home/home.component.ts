import { Component, Input, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { HomeService } from './home.service';
import { Account, LoginModalService, Principal } from '../shared';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: [
    'home.scss'
  ]

})

export class HomeComponent implements OnInit {
  account: Account;
  modalRef: NgbModalRef;
  states: any;
  isNavbarCollapsed: boolean;
  public loading = false;
  
  constructor(
    private principal: Principal,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private homeService: HomeService
  ) {
    
  }
  collapseNavbar() {
   
    this.isNavbarCollapsed = true;
}

  ngOnInit() {
   // this.loading=true;
    this.principal.identity().then((account) => {
      this.account = account;
      
    //  console.log(JSON.stringify(this.account))
    });
   // this.loading=false;
    this.registerAuthenticationSuccess();
    
  }

  registerAuthenticationSuccess() {
   
    this.eventManager.subscribe('authenticationSuccess', (message) => {
      this.principal.identity().then((account) => {
        this.account = account;
       
      });
     
    });
   // this.loading = true;
  }
  isState = false;
  isAuthenticated() {
    if (!this.isState && this.principal.isAuthenticated()) {
      this.getAllStates();
      this.isState = true;
      
    }


    return this.principal.isAuthenticated();
  }
  getAllStates() {
   // this.loading = true;
    this.homeService.viewStates().finally(() => {
       //this.loading = false;
  
    }).
      subscribe((res) => {

        this.states = res; console.log(res)
      }, (error) => console.log(error))
  }
  login() {
    this.modalRef = this.loginModalService.open();
  }
}
