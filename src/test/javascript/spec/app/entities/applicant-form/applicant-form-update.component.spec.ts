import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ApplicantFormUpdateComponent } from 'app/entities/applicant-form/applicant-form-update.component';
import { ApplicantFormService } from 'app/entities/applicant-form/applicant-form.service';
import { ApplicantForm } from 'app/shared/model/applicant-form.model';

describe('Component Tests', () => {
  describe('ApplicantForm Management Update Component', () => {
    let comp: ApplicantFormUpdateComponent;
    let fixture: ComponentFixture<ApplicantFormUpdateComponent>;
    let service: ApplicantFormService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ApplicantFormUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApplicantFormUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApplicantFormUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApplicantFormService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApplicantForm(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApplicantForm();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
