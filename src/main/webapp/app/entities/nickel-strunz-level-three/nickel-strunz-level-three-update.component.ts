import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INickelStrunzLevelThree, NickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';
import { NickelStrunzLevelThreeService } from './nickel-strunz-level-three.service';
import { INickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';
import { NickelStrunzLevelTwoService } from 'app/entities/nickel-strunz-level-two/nickel-strunz-level-two.service';

@Component({
  selector: 'jhi-nickel-strunz-level-three-update',
  templateUrl: './nickel-strunz-level-three-update.component.html'
})
export class NickelStrunzLevelThreeUpdateComponent implements OnInit {
  isSaving = false;
  nickelstrunzleveltwos: INickelStrunzLevelTwo[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    name: [null, [Validators.required]],
    shortName: [],
    parentId: [null, Validators.required]
  });

  constructor(
    protected nickelStrunzLevelThreeService: NickelStrunzLevelThreeService,
    protected nickelStrunzLevelTwoService: NickelStrunzLevelTwoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nickelStrunzLevelThree }) => {
      this.updateForm(nickelStrunzLevelThree);

      this.nickelStrunzLevelTwoService
        .query()
        .subscribe((res: HttpResponse<INickelStrunzLevelTwo[]>) => (this.nickelstrunzleveltwos = res.body || []));
    });
  }

  updateForm(nickelStrunzLevelThree: INickelStrunzLevelThree): void {
    this.editForm.patchValue({
      id: nickelStrunzLevelThree.id,
      code: nickelStrunzLevelThree.code,
      name: nickelStrunzLevelThree.name,
      shortName: nickelStrunzLevelThree.shortName,
      parentId: nickelStrunzLevelThree.parentId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nickelStrunzLevelThree = this.createFromForm();
    if (nickelStrunzLevelThree.id !== undefined) {
      this.subscribeToSaveResponse(this.nickelStrunzLevelThreeService.update(nickelStrunzLevelThree));
    } else {
      this.subscribeToSaveResponse(this.nickelStrunzLevelThreeService.create(nickelStrunzLevelThree));
    }
  }

  private createFromForm(): INickelStrunzLevelThree {
    return {
      ...new NickelStrunzLevelThree(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      shortName: this.editForm.get(['shortName'])!.value,
      parentId: this.editForm.get(['parentId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INickelStrunzLevelThree>>): void {
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

  trackById(index: number, item: INickelStrunzLevelTwo): any {
    return item.id;
  }
}
