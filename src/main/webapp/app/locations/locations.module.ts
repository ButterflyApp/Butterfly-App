import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButterflyAppSharedModule } from '../shared';

import { LOCATIONS_ROUTE, LocationsComponent } from './';
import { HttpModule } from '@angular/http';
import { LocationsService } from './locations.service';
@NgModule({
    imports: [
        ButterflyAppSharedModule,
        HttpModule,
        RouterModule.forRoot([ LOCATIONS_ROUTE ], { useHash: true })
    ],
    declarations: [
        LocationsComponent,
    ],
    entryComponents: [
    ],
    providers: [
        LocationsService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppLocationsModule {}
