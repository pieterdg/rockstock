import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { StorageLocationDetailComponent } from 'app/entities/storage-location/storage-location-detail.component';
import { StorageLocation } from 'app/shared/model/storage-location.model';

describe('Component Tests', () => {
  describe('StorageLocation Management Detail Component', () => {
    let comp: StorageLocationDetailComponent;
    let fixture: ComponentFixture<StorageLocationDetailComponent>;
    const route = ({ data: of({ storageLocation: new StorageLocation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [StorageLocationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(StorageLocationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StorageLocationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load storageLocation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.storageLocation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
