import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMineral } from 'app/shared/model/mineral.model';
import { MineralService } from './mineral.service';

@Component({
  templateUrl: './mineral-delete-dialog.component.html'
})
export class MineralDeleteDialogComponent {
  mineral?: IMineral;

  constructor(protected mineralService: MineralService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mineralService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mineralListModification');
      this.activeModal.close();
    });
  }
}
