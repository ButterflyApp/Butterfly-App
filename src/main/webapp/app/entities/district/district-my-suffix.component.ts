import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { DistrictMySuffix } from './district-my-suffix.model';
import { DistrictMySuffixService } from './district-my-suffix.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-district-my-suffix',
    templateUrl: './district-my-suffix.component.html'
})
export class DistrictMySuffixComponent implements OnInit, OnDestroy {
districts: DistrictMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private districtService: DistrictMySuffixService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.districtService.query().subscribe(
            (res: ResponseWrapper) => {
                this.districts = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDistricts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: DistrictMySuffix) {
        return item.id;
    }
    registerChangeInDistricts() {
        this.eventSubscriber = this.eventManager.subscribe('districtListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
