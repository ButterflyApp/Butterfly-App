import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButterflyAppSharedModule } from '../shared';

import { PROFILE_ROUTE, ProfileComponent } from './';
import { HttpModule } from '@angular/http';
import { ProfileService } from './profile.service';
@NgModule({
    imports: [
        ButterflyAppSharedModule,
        HttpModule,
        RouterModule.forRoot([ PROFILE_ROUTE ], { useHash: true })
    ],
    declarations: [
        ProfileComponent,
    ],
    entryComponents: [
    ],
    providers: [
        ProfileService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppProfileModule {}
