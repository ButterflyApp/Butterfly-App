import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { DistrictMySuffix } from './district-my-suffix.model';
import { DistrictMySuffixService } from './district-my-suffix.service';

@Component({
    selector: 'jhi-district-my-suffix-detail',
    templateUrl: './district-my-suffix-detail.component.html'
})
export class DistrictMySuffixDetailComponent implements OnInit, OnDestroy {

    district: DistrictMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private districtService: DistrictMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDistricts();
    }

    load(id) {
        this.districtService.find(id).subscribe((district) => {
            this.district = district;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDistricts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'districtListModification',
            (response) => this.load(this.district.id)
        );
    }
}
