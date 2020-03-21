import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { SpecimenDetailComponent } from 'app/entities/specimen/specimen-detail.component';
import { Specimen } from 'app/shared/model/specimen.model';

describe('Component Tests', () => {
  describe('Specimen Management Detail Component', () => {
    let comp: SpecimenDetailComponent;
    let fixture: ComponentFixture<SpecimenDetailComponent>;
    const route = ({ data: of({ specimen: new Specimen(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [SpecimenDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SpecimenDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecimenDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load specimen on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specimen).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
