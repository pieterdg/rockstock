import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { MineralDetailComponent } from 'app/entities/mineral/mineral-detail.component';
import { Mineral } from 'app/shared/model/mineral.model';

describe('Component Tests', () => {
  describe('Mineral Management Detail Component', () => {
    let comp: MineralDetailComponent;
    let fixture: ComponentFixture<MineralDetailComponent>;
    const route = ({ data: of({ mineral: new Mineral(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [MineralDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MineralDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MineralDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mineral on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mineral).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
