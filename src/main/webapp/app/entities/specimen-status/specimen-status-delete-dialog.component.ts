import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpecimenStatus } from 'app/shared/model/specimen-status.model';
import { SpecimenStatusService } from './specimen-status.service';

@Component({
  templateUrl: './specimen-status-delete-dialog.component.html'
})
export class SpecimenStatusDeleteDialogComponent {
  specimenStatus?: ISpecimenStatus;

  constructor(
    protected specimenStatusService: SpecimenStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.specimenStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('specimenStatusListModification');
      this.activeModal.close();
    });
  }
}
