import { Component, Input, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { StatesService } from './states.service';
import { Account, LoginModalService, Principal } from '../shared';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'jhi-states',
  templateUrl: './states.component.html',
  styleUrls: [
    'states.scss'
  ]

})

export class StatesComponent implements OnInit {
  account: Account;
  modalRef: NgbModalRef;
  districts: any;
  ids: number;
  isNavbarCollapsed: boolean;
  public loading = false;

  constructor(
    route: ActivatedRoute,
    private principal: Principal,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private statesService: StatesService,

  ) {
    this.ids = route.snapshot.params['pid'];
    this.loading = true;
    this.statesService.findDistrict(this.ids).finally(() => {
      this.loading = false;
    })
      .subscribe(res => {
        this.districts = res; console.log(res)
      }, error => console.log(error))


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
