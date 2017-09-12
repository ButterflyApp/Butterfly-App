import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { StateMySuffix } from './state-my-suffix.model';
import { StateMySuffixService } from './state-my-suffix.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-state-my-suffix',
    templateUrl: './state-my-suffix.component.html'
})
export class StateMySuffixComponent implements OnInit, OnDestroy {
states: StateMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private stateService: StateMySuffixService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.stateService.query().subscribe(
            (res: ResponseWrapper) => {
                this.states = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInStates();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: StateMySuffix) {
        return item.id;
    }
    registerChangeInStates() {
        this.eventSubscriber = this.eventManager.subscribe('stateListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
