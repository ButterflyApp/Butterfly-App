import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { StateMySuffix } from './state-my-suffix.model';
import { StateMySuffixService } from './state-my-suffix.service';

@Component({
    selector: 'jhi-state-my-suffix-detail',
    templateUrl: './state-my-suffix-detail.component.html'
})
export class StateMySuffixDetailComponent implements OnInit, OnDestroy {

    state: StateMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private stateService: StateMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStates();
    }

    load(id) {
        this.stateService.find(id).subscribe((state) => {
            this.state = state;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStates() {
        this.eventSubscriber = this.eventManager.subscribe(
            'stateListModification',
            (response) => this.load(this.state.id)
        );
    }
}
