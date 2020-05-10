import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { SpecimenLocationUpdateComponent } from 'app/entities/specimen-location/specimen-location-update.component';
import { SpecimenLocationService } from 'app/entities/specimen-location/specimen-location.service';
import { SpecimenLocation } from 'app/shared/model/specimen-location.model';

describe('Component Tests', () => {
  describe('SpecimenLocation Management Update Component', () => {
    let comp: SpecimenLocationUpdateComponent;
    let fixture: ComponentFixture<SpecimenLocationUpdateComponent>;
    let service: SpecimenLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [SpecimenLocationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SpecimenLocationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecimenLocationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecimenLocationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SpecimenLocation(123);
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
        const entity = new SpecimenLocation();
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
