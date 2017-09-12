import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { navbarRoute } from '../app.route';
import { errorRoute } from './';
import { CountryMySuffixDetailComponent } from '../entities/country/country-my-suffix-detail.component'
import { StatesComponent } from '../states/states.component'
import { LocationsComponent } from '../locations/locations.component'
import { PlaceComponent } from '../place/place.component'
const LAYOUT_ROUTES = [
    { path: 'sample', component: CountryMySuffixDetailComponent },
    {path:'states/:pid', component:StatesComponent},
    {path:'states/:pid/locations/:lid', component:LocationsComponent},
    {path:'states/:pid/locations/:lid/place/:id', component:PlaceComponent},
    navbarRoute,
    ...errorRoute
];
const NEXT_ROUT = [
    { path: 'sample', component: CountryMySuffixDetailComponent },
    {path:'states/:pid', component:StatesComponent},
    {path:'locations/:lid', component:LocationsComponent},
    {path:'place/:id', component:PlaceComponent},
    navbarRoute,
    ...errorRoute
];

@NgModule({
    imports: [
        RouterModule.forRoot(LAYOUT_ROUTES, { useHash: true })
    ],
    exports: [
        RouterModule
    ]
})
export class LayoutRoutingModule { }
