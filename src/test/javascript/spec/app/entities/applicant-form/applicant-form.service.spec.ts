import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ApplicantFormService } from 'app/entities/applicant-form/applicant-form.service';
import { IApplicantForm, ApplicantForm } from 'app/shared/model/applicant-form.model';

describe('Service Tests', () => {
  describe('ApplicantForm Service', () => {
    let injector: TestBed;
    let service: ApplicantFormService;
    let httpMock: HttpTestingController;
    let elemDefault: IApplicantForm;
    let expectedResult: IApplicantForm | IApplicantForm[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ApplicantFormService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ApplicantForm(
        0,
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        false,
        false,
        false,
        false,
        false,
        false,
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ApplicantForm', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate
          },
          returnedFromService
        );

        service.create(new ApplicantForm()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ApplicantForm', () => {
        const returnedFromService = Object.assign(
          {
            userImage: 'BBBBBB',
            name: 'BBBBBB',
            email: 'BBBBBB',
            recevierEmail: 'BBBBBB',
            phone: 'BBBBBB',
            address: 'BBBBBB',
            gender: 'BBBBBB',
            placeOfBirth: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            visaDetails: 'BBBBBB',
            citizenship: 'BBBBBB',
            resume: 'BBBBBB',
            employeeApplication: true,
            employeeOrientation: true,
            employeeIdentification: true,
            securityClearance: true,
            medicalClearance: true,
            employeeWellness: true,
            emergencyContact: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ApplicantForm', () => {
        const returnedFromService = Object.assign(
          {
            userImage: 'BBBBBB',
            name: 'BBBBBB',
            email: 'BBBBBB',
            recevierEmail: 'BBBBBB',
            phone: 'BBBBBB',
            address: 'BBBBBB',
            gender: 'BBBBBB',
            placeOfBirth: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            visaDetails: 'BBBBBB',
            citizenship: 'BBBBBB',
            resume: 'BBBBBB',
            employeeApplication: true,
            employeeOrientation: true,
            employeeIdentification: true,
            securityClearance: true,
            medicalClearance: true,
            employeeWellness: true,
            emergencyContact: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ApplicantForm', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
