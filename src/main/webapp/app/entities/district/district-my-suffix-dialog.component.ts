import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { DistrictMySuffix } from './district-my-suffix.model';
import { DistrictMySuffixPopupService } from './district-my-suffix-popup.service';
import { DistrictMySuffixService } from './district-my-suffix.service';
import { StateMySuffix, StateMySuffixService } from '../state';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-district-my-suffix-dialog',
    templateUrl: './district-my-suffix-dialog.component.html'
})
export class DistrictMySuffixDialogComponent implements OnInit {

    district: DistrictMySuffix;
    isSaving: boolean;

    states: StateMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private districtService: DistrictMySuffixService,
        private stateService: StateMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.stateService.query()
            .subscribe((res: ResponseWrapper) => { this.states = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.district.id !== undefined) {
            this.subscribeToSaveResponse(
                this.districtService.update(this.district));
        } else {
            this.subscribeToSaveResponse(
                this.districtService.create(this.district));
        }
    }

    private subscribeToSaveResponse(result: Observable<DistrictMySuffix>) {
        result.subscribe((res: DistrictMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: DistrictMySuffix) {
        this.eventManager.broadcast({ name: 'districtListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackStateById(index: number, item: StateMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-district-my-suffix-popup',
    template: ''
})
export class DistrictMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private districtPopupService: DistrictMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.districtPopupService
                    .open(DistrictMySuffixDialogComponent as Component, params['id']);
            } else {
                this.districtPopupService
                    .open(DistrictMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
