import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISpecimen, Specimen } from 'app/shared/model/specimen.model';
import { SpecimenService } from './specimen.service';
import { ISpecimenStatus } from 'app/shared/model/specimen-status.model';
import { SpecimenStatusService } from 'app/entities/specimen-status/specimen-status.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { IMineral } from 'app/shared/model/mineral.model';
import { MineralService } from 'app/entities/mineral/mineral.service';

type SelectableEntity = ISpecimenStatus | ILocation | IMineral;

@Component({
  selector: 'jhi-specimen-update',
  templateUrl: './specimen-update.component.html'
})
export class SpecimenUpdateComponent implements OnInit {
  isSaving = false;
  specimenstatuses: ISpecimenStatus[] = [];
  locations: ILocation[] = [];
  minerals: IMineral[] = [];
  acquiredDateDp: any;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    acquiredDate: [],
    acquiredAt: [],
    acquiredPrice: [],
    acquiredBy: [],
    acquiredFrom: [],
    remark: [],
    fluorescent: [],
    statusId: [],
    locationId: [],
    minerals: []
  });

  constructor(
    protected specimenService: SpecimenService,
    protected specimenStatusService: SpecimenStatusService,
    protected locationService: LocationService,
    protected mineralService: MineralService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specimen }) => {
      this.updateForm(specimen);

      this.specimenStatusService.query().subscribe((res: HttpResponse<ISpecimenStatus[]>) => (this.specimenstatuses = res.body || []));

      this.locationService.query().subscribe((res: HttpResponse<ILocation[]>) => (this.locations = res.body || []));

      this.mineralService.query().subscribe((res: HttpResponse<IMineral[]>) => (this.minerals = res.body || []));
    });
  }

  updateForm(specimen: ISpecimen): void {
    this.editForm.patchValue({
      id: specimen.id,
      code: specimen.code,
      acquiredDate: specimen.acquiredDate,
      acquiredAt: specimen.acquiredAt,
      acquiredPrice: specimen.acquiredPrice,
      acquiredBy: specimen.acquiredBy,
      acquiredFrom: specimen.acquiredFrom,
      remark: specimen.remark,
      fluorescent: specimen.fluorescent,
      statusId: specimen.statusId,
      locationId: specimen.locationId,
      minerals: specimen.minerals
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const specimen = this.createFromForm();
    if (specimen.id !== undefined) {
      this.subscribeToSaveResponse(this.specimenService.update(specimen));
    } else {
      this.subscribeToSaveResponse(this.specimenService.create(specimen));
    }
  }

  private createFromForm(): ISpecimen {
    return {
      ...new Specimen(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      acquiredDate: this.editForm.get(['acquiredDate'])!.value,
      acquiredAt: this.editForm.get(['acquiredAt'])!.value,
      acquiredPrice: this.editForm.get(['acquiredPrice'])!.value,
      acquiredBy: this.editForm.get(['acquiredBy'])!.value,
      acquiredFrom: this.editForm.get(['acquiredFrom'])!.value,
      remark: this.editForm.get(['remark'])!.value,
      fluorescent: this.editForm.get(['fluorescent'])!.value,
      statusId: this.editForm.get(['statusId'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
      minerals: this.editForm.get(['minerals'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecimen>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IMineral[], option: IMineral): IMineral {
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
