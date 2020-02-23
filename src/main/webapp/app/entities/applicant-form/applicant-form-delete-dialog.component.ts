import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApplicantForm } from 'app/shared/model/applicant-form.model';
import { ApplicantFormService } from './applicant-form.service';

@Component({
  templateUrl: './applicant-form-delete-dialog.component.html'
})
export class ApplicantFormDeleteDialogComponent {
  applicantForm?: IApplicantForm;

  constructor(
    protected applicantFormService: ApplicantFormService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.applicantFormService.delete(id).subscribe(() => {
      this.eventManager.broadcast('applicantFormListModification');
      this.activeModal.close();
    });
  }
}
