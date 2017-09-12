import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DistrictMySuffixComponent } from './district-my-suffix.component';
import { DistrictMySuffixDetailComponent } from './district-my-suffix-detail.component';
import { DistrictMySuffixPopupComponent } from './district-my-suffix-dialog.component';
import { DistrictMySuffixDeletePopupComponent } from './district-my-suffix-delete-dialog.component';

export const districtRoute: Routes = [
    {
        path: 'district-my-suffix',
        component: DistrictMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.district.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'district-my-suffix/:id',
        component: DistrictMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.district.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const districtPopupRoute: Routes = [
    {
        path: 'district-my-suffix-new',
        component: DistrictMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.district.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'district-my-suffix/:id/edit',
        component: DistrictMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.district.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'district-my-suffix/:id/delete',
        component: DistrictMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'butterflyApp.district.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
