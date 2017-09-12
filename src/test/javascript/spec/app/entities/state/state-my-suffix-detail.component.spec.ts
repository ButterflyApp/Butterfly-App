/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ButterflyAppTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { StateMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/state/state-my-suffix-detail.component';
import { StateMySuffixService } from '../../../../../../main/webapp/app/entities/state/state-my-suffix.service';
import { StateMySuffix } from '../../../../../../main/webapp/app/entities/state/state-my-suffix.model';

describe('Component Tests', () => {

    describe('StateMySuffix Management Detail Component', () => {
        let comp: StateMySuffixDetailComponent;
        let fixture: ComponentFixture<StateMySuffixDetailComponent>;
        let service: StateMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ButterflyAppTestModule],
                declarations: [StateMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    StateMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(StateMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StateMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StateMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new StateMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.state).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
