import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { NickelStrunzLevelTwoUpdateComponent } from 'app/entities/nickel-strunz-level-two/nickel-strunz-level-two-update.component';
import { NickelStrunzLevelTwoService } from 'app/entities/nickel-strunz-level-two/nickel-strunz-level-two.service';
import { NickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';

describe('Component Tests', () => {
  describe('NickelStrunzLevelTwo Management Update Component', () => {
    let comp: NickelStrunzLevelTwoUpdateComponent;
    let fixture: ComponentFixture<NickelStrunzLevelTwoUpdateComponent>;
    let service: NickelStrunzLevelTwoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelTwoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NickelStrunzLevelTwoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NickelStrunzLevelTwoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NickelStrunzLevelTwoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NickelStrunzLevelTwo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new NickelStrunzLevelTwo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
