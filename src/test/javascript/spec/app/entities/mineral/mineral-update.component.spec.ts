import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { MineralUpdateComponent } from 'app/entities/mineral/mineral-update.component';
import { MineralService } from 'app/entities/mineral/mineral.service';
import { Mineral } from 'app/shared/model/mineral.model';

describe('Component Tests', () => {
  describe('Mineral Management Update Component', () => {
    let comp: MineralUpdateComponent;
    let fixture: ComponentFixture<MineralUpdateComponent>;
    let service: MineralService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [MineralUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MineralUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MineralUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MineralService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Mineral(123);
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
        const entity = new Mineral();
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
