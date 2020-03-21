import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INickelStrunzLevelOne, NickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';
import { NickelStrunzLevelOneService } from './nickel-strunz-level-one.service';

@Component({
  selector: 'jhi-nickel-strunz-level-one-update',
  templateUrl: './nickel-strunz-level-one-update.component.html'
})
export class NickelStrunzLevelOneUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    name: [null, [Validators.required]]
  });

  constructor(
    protected nickelStrunzLevelOneService: NickelStrunzLevelOneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nickelStrunzLevelOne }) => {
      this.updateForm(nickelStrunzLevelOne);
    });
  }

  updateForm(nickelStrunzLevelOne: INickelStrunzLevelOne): void {
    this.editForm.patchValue({
      id: nickelStrunzLevelOne.id,
      code: nickelStrunzLevelOne.code,
      name: nickelStrunzLevelOne.name
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nickelStrunzLevelOne = this.createFromForm();
    if (nickelStrunzLevelOne.id !== undefined) {
      this.subscribeToSaveResponse(this.nickelStrunzLevelOneService.update(nickelStrunzLevelOne));
    } else {
      this.subscribeToSaveResponse(this.nickelStrunzLevelOneService.create(nickelStrunzLevelOne));
    }
  }

  private createFromForm(): INickelStrunzLevelOne {
    return {
      ...new NickelStrunzLevelOne(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INickelStrunzLevelOne>>): void {
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
