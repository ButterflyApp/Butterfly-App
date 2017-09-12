import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButterflyAppSharedModule } from '../../shared';
import {
    DistrictMySuffixService,
    DistrictMySuffixPopupService,
    DistrictMySuffixComponent,
    DistrictMySuffixDetailComponent,
    DistrictMySuffixDialogComponent,
    DistrictMySuffixPopupComponent,
    DistrictMySuffixDeletePopupComponent,
    DistrictMySuffixDeleteDialogComponent,
    districtRoute,
    districtPopupRoute,
} from './';

const ENTITY_STATES = [
    ...districtRoute,
    ...districtPopupRoute,
];

@NgModule({
    imports: [
        ButterflyAppSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DistrictMySuffixComponent,
        DistrictMySuffixDetailComponent,
        DistrictMySuffixDialogComponent,
        DistrictMySuffixDeleteDialogComponent,
        DistrictMySuffixPopupComponent,
        DistrictMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        DistrictMySuffixComponent,
        DistrictMySuffixDialogComponent,
        DistrictMySuffixPopupComponent,
        DistrictMySuffixDeleteDialogComponent,
        DistrictMySuffixDeletePopupComponent,
    ],
    providers: [
        DistrictMySuffixService,
        DistrictMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppDistrictMySuffixModule {}
