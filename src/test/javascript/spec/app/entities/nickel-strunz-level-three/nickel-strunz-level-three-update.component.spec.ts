import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { NickelStrunzLevelThreeUpdateComponent } from 'app/entities/nickel-strunz-level-three/nickel-strunz-level-three-update.component';
import { NickelStrunzLevelThreeService } from 'app/entities/nickel-strunz-level-three/nickel-strunz-level-three.service';
import { NickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

describe('Component Tests', () => {
  describe('NickelStrunzLevelThree Management Update Component', () => {
    let comp: NickelStrunzLevelThreeUpdateComponent;
    let fixture: ComponentFixture<NickelStrunzLevelThreeUpdateComponent>;
    let service: NickelStrunzLevelThreeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelThreeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NickelStrunzLevelThreeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NickelStrunzLevelThreeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NickelStrunzLevelThreeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NickelStrunzLevelThree(123);
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
        const entity = new NickelStrunzLevelThree();
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
