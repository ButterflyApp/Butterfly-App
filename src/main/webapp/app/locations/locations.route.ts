import { Route } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { LocationsComponent } from './';

export const LOCATIONS_ROUTE: Route = {
    path: '',
    component:LocationsComponent,
    data: {
        authorities: [],
        pageTitle: 'locations.title'
    }
};
