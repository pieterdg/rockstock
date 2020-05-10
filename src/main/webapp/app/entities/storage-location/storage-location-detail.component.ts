import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStorageLocation } from 'app/shared/model/storage-location.model';

@Component({
  selector: 'jhi-storage-location-detail',
  templateUrl: './storage-location-detail.component.html'
})
export class StorageLocationDetailComponent implements OnInit {
  storageLocation: IStorageLocation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ storageLocation }) => (this.storageLocation = storageLocation));
  }

  previousState(): void {
    window.history.back();
  }
}
