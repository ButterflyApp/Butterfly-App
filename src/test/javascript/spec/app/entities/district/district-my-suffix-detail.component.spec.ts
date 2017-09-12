/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ButterflyAppTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DistrictMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/district/district-my-suffix-detail.component';
import { DistrictMySuffixService } from '../../../../../../main/webapp/app/entities/district/district-my-suffix.service';
import { DistrictMySuffix } from '../../../../../../main/webapp/app/entities/district/district-my-suffix.model';

describe('Component Tests', () => {

    describe('DistrictMySuffix Management Detail Component', () => {
        let comp: DistrictMySuffixDetailComponent;
        let fixture: ComponentFixture<DistrictMySuffixDetailComponent>;
        let service: DistrictMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ButterflyAppTestModule],
                declarations: [DistrictMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DistrictMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(DistrictMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DistrictMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DistrictMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new DistrictMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.district).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
