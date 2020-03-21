import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';
import { NickelStrunzLevelOneService } from './nickel-strunz-level-one.service';

@Component({
  templateUrl: './nickel-strunz-level-one-delete-dialog.component.html'
})
export class NickelStrunzLevelOneDeleteDialogComponent {
  nickelStrunzLevelOne?: INickelStrunzLevelOne;

  constructor(
    protected nickelStrunzLevelOneService: NickelStrunzLevelOneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nickelStrunzLevelOneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nickelStrunzLevelOneListModification');
      this.activeModal.close();
    });
  }
}
