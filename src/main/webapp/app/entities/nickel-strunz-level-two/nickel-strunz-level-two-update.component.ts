import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INickelStrunzLevelTwo, NickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';
import { NickelStrunzLevelTwoService } from './nickel-strunz-level-two.service';
import { INickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';
import { NickelStrunzLevelOneService } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one.service';

@Component({
  selector: 'jhi-nickel-strunz-level-two-update',
  templateUrl: './nickel-strunz-level-two-update.component.html'
})
export class NickelStrunzLevelTwoUpdateComponent implements OnInit {
  isSaving = false;
  nickelstrunzlevelones: INickelStrunzLevelOne[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    name: [null, [Validators.required]],
    parentId: [null, Validators.required]
  });

  constructor(
    protected nickelStrunzLevelTwoService: NickelStrunzLevelTwoService,
    protected nickelStrunzLevelOneService: NickelStrunzLevelOneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nickelStrunzLevelTwo }) => {
      this.updateForm(nickelStrunzLevelTwo);

      this.nickelStrunzLevelOneService
        .query()
        .subscribe((res: HttpResponse<INickelStrunzLevelOne[]>) => (this.nickelstrunzlevelones = res.body || []));
    });
  }

  updateForm(nickelStrunzLevelTwo: INickelStrunzLevelTwo): void {
    this.editForm.patchValue({
      id: nickelStrunzLevelTwo.id,
      code: nickelStrunzLevelTwo.code,
      name: nickelStrunzLevelTwo.name,
      parentId: nickelStrunzLevelTwo.parentId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nickelStrunzLevelTwo = this.createFromForm();
    if (nickelStrunzLevelTwo.id !== undefined) {
      this.subscribeToSaveResponse(this.nickelStrunzLevelTwoService.update(nickelStrunzLevelTwo));
    } else {
      this.subscribeToSaveResponse(this.nickelStrunzLevelTwoService.create(nickelStrunzLevelTwo));
    }
  }

  private createFromForm(): INickelStrunzLevelTwo {
    return {
      ...new NickelStrunzLevelTwo(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      parentId: this.editForm.get(['parentId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INickelStrunzLevelTwo>>): void {
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

  trackById(index: number, item: INickelStrunzLevelOne): any {
    return item.id;
  }
}
