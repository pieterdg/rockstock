import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { NickelStrunzLevelOneUpdateComponent } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one-update.component';
import { NickelStrunzLevelOneService } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one.service';
import { NickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';

describe('Component Tests', () => {
  describe('NickelStrunzLevelOne Management Update Component', () => {
    let comp: NickelStrunzLevelOneUpdateComponent;
    let fixture: ComponentFixture<NickelStrunzLevelOneUpdateComponent>;
    let service: NickelStrunzLevelOneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelOneUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NickelStrunzLevelOneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NickelStrunzLevelOneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NickelStrunzLevelOneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NickelStrunzLevelOne(123);
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
        const entity = new NickelStrunzLevelOne();
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
