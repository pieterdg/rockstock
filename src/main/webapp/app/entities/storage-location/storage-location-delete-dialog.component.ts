import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStorageLocation } from 'app/shared/model/storage-location.model';
import { StorageLocationService } from './storage-location.service';

@Component({
  templateUrl: './storage-location-delete-dialog.component.html'
})
export class StorageLocationDeleteDialogComponent {
  storageLocation?: IStorageLocation;

  constructor(
    protected storageLocationService: StorageLocationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.storageLocationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('storageLocationListModification');
      this.activeModal.close();
    });
  }
}
