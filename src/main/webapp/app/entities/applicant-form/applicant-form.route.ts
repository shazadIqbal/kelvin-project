import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IApplicantForm, ApplicantForm } from 'app/shared/model/applicant-form.model';
import { ApplicantFormService } from './applicant-form.service';
import { ApplicantFormComponent } from './applicant-form.component';
import { ApplicantFormDetailComponent } from './applicant-form-detail.component';
import { ApplicantFormUpdateComponent } from './applicant-form-update.component';

@Injectable({ providedIn: 'root' })
export class ApplicantFormResolve implements Resolve<IApplicantForm> {
  constructor(private service: ApplicantFormService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IApplicantForm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((applicantForm: HttpResponse<ApplicantForm>) => {
          if (applicantForm.body) {
            return of(applicantForm.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ApplicantForm());
  }
}

export const applicantFormRoute: Routes = [
  {
    path: '',
    component: ApplicantFormComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterApp.applicantForm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApplicantFormDetailComponent,
    resolve: {
      applicantForm: ApplicantFormResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.applicantForm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApplicantFormUpdateComponent,
    resolve: {
      applicantForm: ApplicantFormResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.applicantForm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApplicantFormUpdateComponent,
    resolve: {
      applicantForm: ApplicantFormResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.applicantForm.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
