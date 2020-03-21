import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { SpecimenStatusUpdateComponent } from 'app/entities/specimen-status/specimen-status-update.component';
import { SpecimenStatusService } from 'app/entities/specimen-status/specimen-status.service';
import { SpecimenStatus } from 'app/shared/model/specimen-status.model';

describe('Component Tests', () => {
  describe('SpecimenStatus Management Update Component', () => {
    let comp: SpecimenStatusUpdateComponent;
    let fixture: ComponentFixture<SpecimenStatusUpdateComponent>;
    let service: SpecimenStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [SpecimenStatusUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SpecimenStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecimenStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecimenStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SpecimenStatus(123);
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
        const entity = new SpecimenStatus();
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
