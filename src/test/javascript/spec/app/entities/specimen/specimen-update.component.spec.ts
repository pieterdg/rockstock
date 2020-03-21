import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { SpecimenUpdateComponent } from 'app/entities/specimen/specimen-update.component';
import { SpecimenService } from 'app/entities/specimen/specimen.service';
import { Specimen } from 'app/shared/model/specimen.model';

describe('Component Tests', () => {
  describe('Specimen Management Update Component', () => {
    let comp: SpecimenUpdateComponent;
    let fixture: ComponentFixture<SpecimenUpdateComponent>;
    let service: SpecimenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [SpecimenUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SpecimenUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecimenUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecimenService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Specimen(123);
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
        const entity = new Specimen();
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
