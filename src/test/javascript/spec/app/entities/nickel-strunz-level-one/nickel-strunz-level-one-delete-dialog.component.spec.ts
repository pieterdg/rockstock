import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RockstockTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { NickelStrunzLevelOneDeleteDialogComponent } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one-delete-dialog.component';
import { NickelStrunzLevelOneService } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one.service';

describe('Component Tests', () => {
  describe('NickelStrunzLevelOne Management Delete Component', () => {
    let comp: NickelStrunzLevelOneDeleteDialogComponent;
    let fixture: ComponentFixture<NickelStrunzLevelOneDeleteDialogComponent>;
    let service: NickelStrunzLevelOneService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RockstockTestModule],
        declarations: [NickelStrunzLevelOneDeleteDialogComponent]
      })
        .overrideTemplate(NickelStrunzLevelOneDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NickelStrunzLevelOneDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NickelStrunzLevelOneService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
