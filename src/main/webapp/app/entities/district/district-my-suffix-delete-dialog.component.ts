import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DistrictMySuffix } from './district-my-suffix.model';
import { DistrictMySuffixPopupService } from './district-my-suffix-popup.service';
import { DistrictMySuffixService } from './district-my-suffix.service';

@Component({
    selector: 'jhi-district-my-suffix-delete-dialog',
    templateUrl: './district-my-suffix-delete-dialog.component.html'
})
export class DistrictMySuffixDeleteDialogComponent {

    district: DistrictMySuffix;

    constructor(
        private districtService: DistrictMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.districtService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'districtListModification',
                content: 'Deleted an district'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-district-my-suffix-delete-popup',
    template: ''
})
export class DistrictMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private districtPopupService: DistrictMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.districtPopupService
                .open(DistrictMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
