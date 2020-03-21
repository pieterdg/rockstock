import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISeries, Series } from 'app/shared/model/series.model';
import { SeriesService } from './series.service';
import { ISpecimen } from 'app/shared/model/specimen.model';
import { SpecimenService } from 'app/entities/specimen/specimen.service';

@Component({
  selector: 'jhi-series-update',
  templateUrl: './series-update.component.html'
})
export class SeriesUpdateComponent implements OnInit {
  isSaving = false;
  specimen: ISpecimen[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [],
    specimen: []
  });

  constructor(
    protected seriesService: SeriesService,
    protected specimenService: SpecimenService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ series }) => {
      this.updateForm(series);

      this.specimenService.query().subscribe((res: HttpResponse<ISpecimen[]>) => (this.specimen = res.body || []));
    });
  }

  updateForm(series: ISeries): void {
    this.editForm.patchValue({
      id: series.id,
      name: series.name,
      description: series.description,
      specimen: series.specimen
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const series = this.createFromForm();
    if (series.id !== undefined) {
      this.subscribeToSaveResponse(this.seriesService.update(series));
    } else {
      this.subscribeToSaveResponse(this.seriesService.create(series));
    }
  }

  private createFromForm(): ISeries {
    return {
      ...new Series(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      specimen: this.editForm.get(['specimen'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISeries>>): void {
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

  trackById(index: number, item: ISpecimen): any {
    return item.id;
  }

  getSelected(selectedVals: ISpecimen[], option: ISpecimen): ISpecimen {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
