import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RockstockTestModule } from '../../../test.module';
import { StorageLocationUpdateComponent } from 'app/entities/storage-location/storage-location-update.component';
import { StorageLocationService } from 'app/entities/storage-location/storage-location.service';
import { StorageLocation } from 'app/shared/model/storage-location.model';

describe('Component Tests', () => {
  describe('StorageLocation Management Update Component', () => {
    let comp: StorageLocationUpdateComponent;
    let fixture: ComponentFixture<StorageLocationUpdateComponent>;
    let service: StorageLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [StorageLocationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(StorageLocationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StorageLocationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StorageLocationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StorageLocation(123);
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
        const entity = new StorageLocation();
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
