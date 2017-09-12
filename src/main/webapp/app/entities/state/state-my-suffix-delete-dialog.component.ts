import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StateMySuffix } from './state-my-suffix.model';
import { StateMySuffixPopupService } from './state-my-suffix-popup.service';
import { StateMySuffixService } from './state-my-suffix.service';

@Component({
    selector: 'jhi-state-my-suffix-delete-dialog',
    templateUrl: './state-my-suffix-delete-dialog.component.html'
})
export class StateMySuffixDeleteDialogComponent {

    state: StateMySuffix;

    constructor(
        private stateService: StateMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stateService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'stateListModification',
                content: 'Deleted an state'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-state-my-suffix-delete-popup',
    template: ''
})
export class StateMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private statePopupService: StateMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.statePopupService
                .open(StateMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
