import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButterflyAppSharedModule } from '../shared';

import { STATES_ROUTE, StatesComponent } from './';
import { HttpModule } from '@angular/http';
import { StatesService } from './states.service';
@NgModule({
    imports: [
        ButterflyAppSharedModule,
        HttpModule,
        RouterModule.forRoot([ STATES_ROUTE ], { useHash: true })
    ],
    declarations: [
        StatesComponent,
    ],
    entryComponents: [
    ],
    providers: [
        StatesService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppStatesModule {}
