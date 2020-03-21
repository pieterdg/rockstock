import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';
import { NickelStrunzLevelTwoService } from './nickel-strunz-level-two.service';

@Component({
  templateUrl: './nickel-strunz-level-two-delete-dialog.component.html'
})
export class NickelStrunzLevelTwoDeleteDialogComponent {
  nickelStrunzLevelTwo?: INickelStrunzLevelTwo;

  constructor(
    protected nickelStrunzLevelTwoService: NickelStrunzLevelTwoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nickelStrunzLevelTwoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nickelStrunzLevelTwoListModification');
      this.activeModal.close();
    });
  }
}
