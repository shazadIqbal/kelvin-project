import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { ApplicantFormDetailComponent } from 'app/entities/applicant-form/applicant-form-detail.component';
import { ApplicantForm } from 'app/shared/model/applicant-form.model';

describe('Component Tests', () => {
  describe('ApplicantForm Management Detail Component', () => {
    let comp: ApplicantFormDetailComponent;
    let fixture: ComponentFixture<ApplicantFormDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ applicantForm: new ApplicantForm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ApplicantFormDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApplicantFormDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApplicantFormDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load applicantForm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.applicantForm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
