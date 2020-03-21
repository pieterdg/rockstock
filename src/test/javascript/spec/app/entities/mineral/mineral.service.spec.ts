import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MineralService } from 'app/entities/mineral/mineral.service';
import { IMineral, Mineral } from 'app/shared/model/mineral.model';

describe('Service Tests', () => {
  describe('Mineral Service', () => {
    let injector: TestBed;
    let service: MineralService;
    let httpMock: HttpTestingController;
    let elemDefault: IMineral;
    let expectedResult: IMineral | IMineral[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MineralService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Mineral(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Mineral', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Mineral()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Mineral', () => {
        const returnedFromService = Object.assign(
          {
            dutchName: 'BBBBBB',
            formula: 'BBBBBB',
            hardnessMin: 1,
            hardnessMax: 1,
            crystalSystem: 'BBBBBB',
            nickelStruntzLevelFour: 'BBBBBB',
            mindatUrl: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Mineral', () => {
        const returnedFromService = Object.assign(
          {
            dutchName: 'BBBBBB',
            formula: 'BBBBBB',
            hardnessMin: 1,
            hardnessMax: 1,
            crystalSystem: 'BBBBBB',
            nickelStruntzLevelFour: 'BBBBBB',
            mindatUrl: 'BBBBBB'
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

      it('should delete a Mineral', () => {
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
