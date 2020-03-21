import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISpecimenStatus, SpecimenStatus } from 'app/shared/model/specimen-status.model';
import { SpecimenStatusService } from './specimen-status.service';

@Component({
  selector: 'jhi-specimen-status-update',
  templateUrl: './specimen-status-update.component.html'
})
export class SpecimenStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]]
  });

  constructor(protected specimenStatusService: SpecimenStatusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specimenStatus }) => {
      this.updateForm(specimenStatus);
    });
  }

  updateForm(specimenStatus: ISpecimenStatus): void {
    this.editForm.patchValue({
      id: specimenStatus.id,
      name: specimenStatus.name
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const specimenStatus = this.createFromForm();
    if (specimenStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.specimenStatusService.update(specimenStatus));
    } else {
      this.subscribeToSaveResponse(this.specimenStatusService.create(specimenStatus));
    }
  }

  private createFromForm(): ISpecimenStatus {
    return {
      ...new SpecimenStatus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecimenStatus>>): void {
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
