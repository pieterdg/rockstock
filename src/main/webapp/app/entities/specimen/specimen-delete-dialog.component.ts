import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpecimen } from 'app/shared/model/specimen.model';
import { SpecimenService } from './specimen.service';

@Component({
  templateUrl: './specimen-delete-dialog.component.html'
})
export class SpecimenDeleteDialogComponent {
  specimen?: ISpecimen;

  constructor(protected specimenService: SpecimenService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.specimenService.delete(id).subscribe(() => {
      this.eventManager.broadcast('specimenListModification');
      this.activeModal.close();
    });
  }
}
