import { Route } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { ProfileComponent } from './';

export const PROFILE_ROUTE: Route = {
    path: '',
    component: ProfileComponent,
    data: {
        authorities: [],
        pageTitle: 'profile.title'
    }
};
