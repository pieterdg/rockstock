import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStorageLocation, StorageLocation } from 'app/shared/model/storage-location.model';
import { StorageLocationService } from './storage-location.service';

@Component({
  selector: 'jhi-storage-location-update',
  templateUrl: './storage-location-update.component.html'
})
export class StorageLocationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]]
  });

  constructor(
    protected storageLocationService: StorageLocationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ storageLocation }) => {
      this.updateForm(storageLocation);
    });
  }

  updateForm(storageLocation: IStorageLocation): void {
    this.editForm.patchValue({
      id: storageLocation.id,
      name: storageLocation.name
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const storageLocation = this.createFromForm();
    if (storageLocation.id !== undefined) {
      this.subscribeToSaveResponse(this.storageLocationService.update(storageLocation));
    } else {
      this.subscribeToSaveResponse(this.storageLocationService.create(storageLocation));
    }
  }

  private createFromForm(): IStorageLocation {
    return {
      ...new StorageLocation(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStorageLocation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
