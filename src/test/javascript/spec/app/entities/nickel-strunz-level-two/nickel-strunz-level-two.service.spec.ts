import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NickelStrunzLevelTwoService } from 'app/entities/nickel-strunz-level-two/nickel-strunz-level-two.service';
import { INickelStrunzLevelTwo, NickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';

describe('Service Tests', () => {
  describe('NickelStrunzLevelTwo Service', () => {
    let injector: TestBed;
    let service: NickelStrunzLevelTwoService;
    let httpMock: HttpTestingController;
    let elemDefault: INickelStrunzLevelTwo;
    let expectedResult: INickelStrunzLevelTwo | INickelStrunzLevelTwo[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(NickelStrunzLevelTwoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new NickelStrunzLevelTwo(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a NickelStrunzLevelTwo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new NickelStrunzLevelTwo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a NickelStrunzLevelTwo', () => {
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

      it('should return a list of NickelStrunzLevelTwo', () => {
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

      it('should delete a NickelStrunzLevelTwo', () => {
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
