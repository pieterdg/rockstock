import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { NickelStrunzLevelTwoDetailComponent } from 'app/entities/nickel-strunz-level-two/nickel-strunz-level-two-detail.component';
import { NickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';

describe('Component Tests', () => {
  describe('NickelStrunzLevelTwo Management Detail Component', () => {
    let comp: NickelStrunzLevelTwoDetailComponent;
    let fixture: ComponentFixture<NickelStrunzLevelTwoDetailComponent>;
    const route = ({ data: of({ nickelStrunzLevelTwo: new NickelStrunzLevelTwo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelTwoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NickelStrunzLevelTwoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NickelStrunzLevelTwoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nickelStrunzLevelTwo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nickelStrunzLevelTwo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
