import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { SpecimenStatusDetailComponent } from 'app/entities/specimen-status/specimen-status-detail.component';
import { SpecimenStatus } from 'app/shared/model/specimen-status.model';

describe('Component Tests', () => {
  describe('SpecimenStatus Management Detail Component', () => {
    let comp: SpecimenStatusDetailComponent;
    let fixture: ComponentFixture<SpecimenStatusDetailComponent>;
    const route = ({ data: of({ specimenStatus: new SpecimenStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [SpecimenStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SpecimenStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecimenStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load specimenStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specimenStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
