import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NickelStrunzLevelOneService } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one.service';
import { INickelStrunzLevelOne, NickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';

describe('Service Tests', () => {
  describe('NickelStrunzLevelOne Service', () => {
    let injector: TestBed;
    let service: NickelStrunzLevelOneService;
    let httpMock: HttpTestingController;
    let elemDefault: INickelStrunzLevelOne;
    let expectedResult: INickelStrunzLevelOne | INickelStrunzLevelOne[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(NickelStrunzLevelOneService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new NickelStrunzLevelOne(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a NickelStrunzLevelOne', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new NickelStrunzLevelOne()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a NickelStrunzLevelOne', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            name: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of NickelStrunzLevelOne', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            name: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a NickelStrunzLevelOne', () => {
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
