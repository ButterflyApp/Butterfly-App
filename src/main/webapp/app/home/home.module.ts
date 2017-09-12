import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButterflyAppSharedModule } from '../shared';

import { HOME_ROUTE, HomeComponent } from './';
import { HttpModule } from '@angular/http';
import { HomeService } from './home.service';
@NgModule({
    imports: [
        ButterflyAppSharedModule,
        HttpModule,
        RouterModule.forRoot([ HOME_ROUTE ], { useHash: true })
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
        HomeService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppHomeModule {}
