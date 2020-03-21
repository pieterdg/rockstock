import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { NickelStrunzLevelThreeDetailComponent } from 'app/entities/nickel-strunz-level-three/nickel-strunz-level-three-detail.component';
import { NickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

describe('Component Tests', () => {
  describe('NickelStrunzLevelThree Management Detail Component', () => {
    let comp: NickelStrunzLevelThreeDetailComponent;
    let fixture: ComponentFixture<NickelStrunzLevelThreeDetailComponent>;
    const route = ({ data: of({ nickelStrunzLevelThree: new NickelStrunzLevelThree(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelThreeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NickelStrunzLevelThreeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NickelStrunzLevelThreeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nickelStrunzLevelThree on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nickelStrunzLevelThree).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
