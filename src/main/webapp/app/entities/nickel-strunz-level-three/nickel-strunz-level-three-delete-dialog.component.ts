import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';
import { NickelStrunzLevelThreeService } from './nickel-strunz-level-three.service';

@Component({
  templateUrl: './nickel-strunz-level-three-delete-dialog.component.html'
})
export class NickelStrunzLevelThreeDeleteDialogComponent {
  nickelStrunzLevelThree?: INickelStrunzLevelThree;

  constructor(
    protected nickelStrunzLevelThreeService: NickelStrunzLevelThreeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nickelStrunzLevelThreeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nickelStrunzLevelThreeListModification');
      this.activeModal.close();
    });
  }
}
