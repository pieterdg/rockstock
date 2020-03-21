import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { NickelStrunzLevelOneDetailComponent } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one-detail.component';
import { NickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';

describe('Component Tests', () => {
  describe('NickelStrunzLevelOne Management Detail Component', () => {
    let comp: NickelStrunzLevelOneDetailComponent;
    let fixture: ComponentFixture<NickelStrunzLevelOneDetailComponent>;
    const route = ({ data: of({ nickelStrunzLevelOne: new NickelStrunzLevelOne(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelOneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NickelStrunzLevelOneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NickelStrunzLevelOneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nickelStrunzLevelOne on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nickelStrunzLevelOne).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
