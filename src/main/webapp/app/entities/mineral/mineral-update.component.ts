import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMineral, Mineral } from 'app/shared/model/mineral.model';
import { MineralService } from './mineral.service';
import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';
import { NickelStrunzLevelThreeService } from 'app/entities/nickel-strunz-level-three/nickel-strunz-level-three.service';

@Component({
  selector: 'jhi-mineral-update',
  templateUrl: './mineral-update.component.html'
})
export class MineralUpdateComponent implements OnInit {
  isSaving = false;
  nickelstrunzlevelthrees: INickelStrunzLevelThree[] = [];

  editForm = this.fb.group({
    id: [],
    dutchName: [null, [Validators.required]],
    formula: [null, [Validators.required]],
    hardnessMin: [null, [Validators.required]],
    hardnessMax: [null, [Validators.required]],
    crystalSystem: [null, [Validators.required]],
    nickelStruntzLevelFour: [null, [Validators.required]],
    mindatUrl: [null, [Validators.required]],
    nickelStrunzLevelThreeId: [null, Validators.required]
  });

  constructor(
    protected mineralService: MineralService,
    protected nickelStrunzLevelThreeService: NickelStrunzLevelThreeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mineral }) => {
      this.updateForm(mineral);

      this.nickelStrunzLevelThreeService
        .query()
        .subscribe((res: HttpResponse<INickelStrunzLevelThree[]>) => (this.nickelstrunzlevelthrees = res.body || []));
    });
  }

  updateForm(mineral: IMineral): void {
    this.editForm.patchValue({
      id: mineral.id,
      dutchName: mineral.dutchName,
      formula: mineral.formula,
      hardnessMin: mineral.hardnessMin,
      hardnessMax: mineral.hardnessMax,
      crystalSystem: mineral.crystalSystem,
      nickelStruntzLevelFour: mineral.nickelStruntzLevelFour,
      mindatUrl: mineral.mindatUrl,
      nickelStrunzLevelThreeId: mineral.nickelStrunzLevelThreeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mineral = this.createFromForm();
    if (mineral.id !== undefined) {
      this.subscribeToSaveResponse(this.mineralService.update(mineral));
    } else {
      this.subscribeToSaveResponse(this.mineralService.create(mineral));
    }
  }

  private createFromForm(): IMineral {
    return {
      ...new Mineral(),
      id: this.editForm.get(['id'])!.value,
      dutchName: this.editForm.get(['dutchName'])!.value,
      formula: this.editForm.get(['formula'])!.value,
      hardnessMin: this.editForm.get(['hardnessMin'])!.value,
      hardnessMax: this.editForm.get(['hardnessMax'])!.value,
      crystalSystem: this.editForm.get(['crystalSystem'])!.value,
      nickelStruntzLevelFour: this.editForm.get(['nickelStruntzLevelFour'])!.value,
      mindatUrl: this.editForm.get(['mindatUrl'])!.value,
      nickelStrunzLevelThreeId: this.editForm.get(['nickelStrunzLevelThreeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMineral>>): void {
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

  trackById(index: number, item: INickelStrunzLevelThree): any {
    return item.id;
  }
}
