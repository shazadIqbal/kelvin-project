import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { ApplicantFormComponent } from './applicant-form.component';
import { ApplicantFormDetailComponent } from './applicant-form-detail.component';
import { ApplicantFormUpdateComponent } from './applicant-form-update.component';
import { ApplicantFormDeleteDialogComponent } from './applicant-form-delete-dialog.component';
import { applicantFormRoute } from './applicant-form.route';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(applicantFormRoute)],
  declarations: [ApplicantFormComponent, ApplicantFormDetailComponent, ApplicantFormUpdateComponent, ApplicantFormDeleteDialogComponent],
  entryComponents: [ApplicantFormDeleteDialogComponent]
})
export class JhipsterApplicantFormModule {}
