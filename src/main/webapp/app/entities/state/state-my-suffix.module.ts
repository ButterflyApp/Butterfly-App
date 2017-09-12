import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButterflyAppSharedModule } from '../../shared';
import {
    StateMySuffixService,
    StateMySuffixPopupService,
    StateMySuffixComponent,
    StateMySuffixDetailComponent,
    StateMySuffixDialogComponent,
    StateMySuffixPopupComponent,
    StateMySuffixDeletePopupComponent,
    StateMySuffixDeleteDialogComponent,
    stateRoute,
    statePopupRoute,
} from './';

const ENTITY_STATES = [
    ...stateRoute,
    ...statePopupRoute,
];

@NgModule({
    imports: [
        ButterflyAppSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        StateMySuffixComponent,
        StateMySuffixDetailComponent,
        StateMySuffixDialogComponent,
        StateMySuffixDeleteDialogComponent,
        StateMySuffixPopupComponent,
        StateMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        StateMySuffixComponent,
        StateMySuffixDialogComponent,
        StateMySuffixPopupComponent,
        StateMySuffixDeleteDialogComponent,
        StateMySuffixDeletePopupComponent,
    ],
    providers: [
        StateMySuffixService,
        StateMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppStateMySuffixModule {}
