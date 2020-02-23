import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IApplicantForm, ApplicantForm } from 'app/shared/model/applicant-form.model';
import { ApplicantFormService } from './applicant-form.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-applicant-form-update',
  templateUrl: './applicant-form-update.component.html'
})
export class ApplicantFormUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    userImage: [],
    userImageContentType: [],
    name: [],
    email: [],
    recevierEmail: [],
    phone: [],
    address: [],
    gender: [],
    placeOfBirth: [],
    dateOfBirth: [],
    visaDetails: [],
    citizenship: [],
    resume: [],
    resumeContentType: [],
    employeeApplication: [],
    employeeOrientation: [],
    employeeIdentification: [],
    securityClearance: [],
    medicalClearance: [],
    employeeWellness: [],
    emergencyContact: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected applicantFormService: ApplicantFormService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ applicantForm }) => {
      if (!applicantForm.id) {
        const today = moment().startOf('day');
        applicantForm.dateOfBirth = today;
      }

      this.updateForm(applicantForm);
    });
  }

  updateForm(applicantForm: IApplicantForm): void {
    this.editForm.patchValue({
      id: applicantForm.id,
      userImage: applicantForm.userImage,
      userImageContentType: applicantForm.userImageContentType,
      name: applicantForm.name,
      email: applicantForm.email,
      recevierEmail: applicantForm.recevierEmail,
      phone: applicantForm.phone,
      address: applicantForm.address,
      gender: applicantForm.gender,
      placeOfBirth: applicantForm.placeOfBirth,
      dateOfBirth: applicantForm.dateOfBirth ? applicantForm.dateOfBirth.format(DATE_TIME_FORMAT) : null,
      visaDetails: applicantForm.visaDetails,
      citizenship: applicantForm.citizenship,
      resume: applicantForm.resume,
      resumeContentType: applicantForm.resumeContentType,
      employeeApplication: applicantForm.employeeApplication,
      employeeOrientation: applicantForm.employeeOrientation,
      employeeIdentification: applicantForm.employeeIdentification,
      securityClearance: applicantForm.securityClearance,
      medicalClearance: applicantForm.medicalClearance,
      employeeWellness: applicantForm.employeeWellness,
      emergencyContact: applicantForm.emergencyContact
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('jhipsterApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const applicantForm = this.createFromForm();
    if (applicantForm.id !== undefined) {
      this.subscribeToSaveResponse(this.applicantFormService.update(applicantForm));
    } else {
      this.subscribeToSaveResponse(this.applicantFormService.create(applicantForm));
    }
  }

  private createFromForm(): IApplicantForm {
    return {
      ...new ApplicantForm(),
      id: this.editForm.get(['id'])!.value,
      userImageContentType: this.editForm.get(['userImageContentType'])!.value,
      userImage: this.editForm.get(['userImage'])!.value,
      name: this.editForm.get(['name'])!.value,
      email: this.editForm.get(['email'])!.value,
      recevierEmail: this.editForm.get(['recevierEmail'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      address: this.editForm.get(['address'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      placeOfBirth: this.editForm.get(['placeOfBirth'])!.value,
      dateOfBirth: this.editForm.get(['dateOfBirth'])!.value
        ? moment(this.editForm.get(['dateOfBirth'])!.value, DATE_TIME_FORMAT)
        : undefined,
      visaDetails: this.editForm.get(['visaDetails'])!.value,
      citizenship: this.editForm.get(['citizenship'])!.value,
      resumeContentType: this.editForm.get(['resumeContentType'])!.value,
      resume: this.editForm.get(['resume'])!.value,
      employeeApplication: this.editForm.get(['employeeApplication'])!.value,
      employeeOrientation: this.editForm.get(['employeeOrientation'])!.value,
      employeeIdentification: this.editForm.get(['employeeIdentification'])!.value,
      securityClearance: this.editForm.get(['securityClearance'])!.value,
      medicalClearance: this.editForm.get(['medicalClearance'])!.value,
      employeeWellness: this.editForm.get(['employeeWellness'])!.value,
      emergencyContact: this.editForm.get(['emergencyContact'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplicantForm>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
