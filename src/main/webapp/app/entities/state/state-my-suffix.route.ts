import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { StateMySuffixComponent } from './state-my-suffix.component';
import { StateMySuffixDetailComponent } from './state-my-suffix-detail.component';
import { StateMySuffixPopupComponent } from './state-my-suffix-dialog.component';
import { StateMySuffixDeletePopupComponent } from './state-my-suffix-delete-dialog.component';

export const stateRoute: Routes = [
    {
        path: 'state-my-suffix',
        component: StateMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.state.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'state-my-suffix/:id',
        component: StateMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.state.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const statePopupRoute: Routes = [
    {
        path: 'state-my-suffix-new',
        component: StateMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.state.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'state-my-suffix/:id/edit',
        component: StateMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.state.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'state-my-suffix/:id/delete',
        component: StateMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.state.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
