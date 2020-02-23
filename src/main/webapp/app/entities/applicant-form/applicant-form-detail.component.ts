import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IApplicantForm } from 'app/shared/model/applicant-form.model';

@Component({
  selector: 'jhi-applicant-form-detail',
  templateUrl: './applicant-form-detail.component.html'
})
export class ApplicantFormDetailComponent implements OnInit {
  applicantForm: IApplicantForm | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ applicantForm }) => (this.applicantForm = applicantForm));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
