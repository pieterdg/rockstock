import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { SpecimenLocationDetailComponent } from 'app/entities/specimen-location/specimen-location-detail.component';
import { SpecimenLocation } from 'app/shared/model/specimen-location.model';

describe('Component Tests', () => {
  describe('SpecimenLocation Management Detail Component', () => {
    let comp: SpecimenLocationDetailComponent;
    let fixture: ComponentFixture<SpecimenLocationDetailComponent>;
    const route = ({ data: of({ specimenLocation: new SpecimenLocation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [SpecimenLocationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SpecimenLocationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecimenLocationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load specimenLocation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specimenLocation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
