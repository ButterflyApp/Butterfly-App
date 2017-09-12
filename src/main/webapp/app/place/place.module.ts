import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButterflyAppSharedModule } from '../shared';

import { PLACE_ROUTE, PlaceComponent } from './';
import { HttpModule } from '@angular/http';
import { PlaceService } from './place.service';
@NgModule({
    imports: [
        ButterflyAppSharedModule,
        HttpModule,
        RouterModule.forRoot([ PLACE_ROUTE ], { useHash: true })
    ],
    declarations: [
        PlaceComponent,
    ],
    entryComponents: [
    ],
    providers: [
        PlaceService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppPlaceModule {}
