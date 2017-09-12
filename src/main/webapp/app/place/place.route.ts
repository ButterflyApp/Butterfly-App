import { Route } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { PlaceComponent } from './';

export const PLACE_ROUTE: Route = {
    path: '',
    component: PlaceComponent,
    data: {
        authorities: [],
        pageTitle: 'place.title'
    }
};
