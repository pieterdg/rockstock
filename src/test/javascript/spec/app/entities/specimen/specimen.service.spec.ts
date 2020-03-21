import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SpecimenService } from 'app/entities/specimen/specimen.service';
import { ISpecimen, Specimen } from 'app/shared/model/specimen.model';

describe('Service Tests', () => {
  describe('Specimen Service', () => {
    let injector: TestBed;
    let service: SpecimenService;
    let httpMock: HttpTestingController;
    let elemDefault: ISpecimen;
    let expectedResult: ISpecimen | ISpecimen[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SpecimenService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Specimen(0, 'AAAAAAA', currentDate, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            acquiredDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Specimen', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            acquiredDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            acquiredDate: currentDate
          },
          returnedFromService
        );

        service.create(new Specimen()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Specimen', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            acquiredDate: currentDate.format(DATE_FORMAT),
            acquiredAt: 'BBBBBB',
            acquiredPrice: 1,
            acquiredBy: 'BBBBBB',
            acquiredFrom: 'BBBBBB',
            remark: 'BBBBBB',
            fluorescent: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            acquiredDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Specimen', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            acquiredDate: currentDate.format(DATE_FORMAT),
            acquiredAt: 'BBBBBB',
            acquiredPrice: 1,
            acquiredBy: 'BBBBBB',
            acquiredFrom: 'BBBBBB',
            remark: 'BBBBBB',
            fluorescent: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            acquiredDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Specimen', () => {
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
