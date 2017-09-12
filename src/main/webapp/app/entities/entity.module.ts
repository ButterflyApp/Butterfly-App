import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ButterflyAppCountryMySuffixModule } from './country/country-my-suffix.module';
import { ButterflyAppStateMySuffixModule } from './state/state-my-suffix.module';
import { ButterflyAppDistrictMySuffixModule } from './district/district-my-suffix.module';
import { ButterflyAppLocationMySuffixModule } from './location/location-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ButterflyAppCountryMySuffixModule,
        ButterflyAppStateMySuffixModule,
        ButterflyAppDistrictMySuffixModule,
        ButterflyAppLocationMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ButterflyAppEntityModule {}
