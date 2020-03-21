import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { RockstockTestModule } from '../../../test.module';
import { NickelStrunzLevelTwoComponent } from 'app/entities/nickel-strunz-level-two/nickel-strunz-level-two.component';
import { NickelStrunzLevelTwoService } from 'app/entities/nickel-strunz-level-two/nickel-strunz-level-two.service';
import { NickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';

describe('Component Tests', () => {
  describe('NickelStrunzLevelTwo Management Component', () => {
    let comp: NickelStrunzLevelTwoComponent;
    let fixture: ComponentFixture<NickelStrunzLevelTwoComponent>;
    let service: NickelStrunzLevelTwoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelTwoComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(NickelStrunzLevelTwoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NickelStrunzLevelTwoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NickelStrunzLevelTwoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NickelStrunzLevelTwo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.nickelStrunzLevelTwos && comp.nickelStrunzLevelTwos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NickelStrunzLevelTwo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.nickelStrunzLevelTwos && comp.nickelStrunzLevelTwos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
