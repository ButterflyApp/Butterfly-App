import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { ButterflyAppSharedModule, UserRouteAccessService } from './shared';
import { ButterflyAppHomeModule } from './home/home.module';
import { ButterflyAppLocationsModule } from './locations/locations.module';
import { ButterflyAppAdminModule } from './admin/admin.module';
import { ButterflyAppAccountModule } from './account/account.module';
import { ButterflyAppEntityModule } from './entities/entity.module';
import { ButterflyAppStatesModule } from './states/states.module';
import { ButterflyAppPlaceModule } from './place/place.module';

import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';
//import { CoreModule } from './core/core.module';
import { LoadingModule } from 'ngx-loading';
// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    
    JhiMainComponent,
    LayoutRoutingModule,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        ButterflyAppSharedModule,
        ButterflyAppHomeModule,
        ButterflyAppLocationsModule,
        ButterflyAppAdminModule,
        ButterflyAppAccountModule,
        ButterflyAppEntityModule,
        ButterflyAppStatesModule,
        ButterflyAppPlaceModule,
        LoadingModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
   
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class ButterflyAppAppModule {}
