import { Route } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { StatesComponent } from './';

export const STATES_ROUTE: Route = {
    path: '',
    component: StatesComponent,
    data: {
        authorities: [],
        pageTitle: 'states.title'
    }
};
