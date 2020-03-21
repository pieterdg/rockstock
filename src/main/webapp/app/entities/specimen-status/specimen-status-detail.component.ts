import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecimenStatus } from 'app/shared/model/specimen-status.model';

@Component({
  selector: 'jhi-specimen-status-detail',
  templateUrl: './specimen-status-detail.component.html'
})
export class SpecimenStatusDetailComponent implements OnInit {
  specimenStatus: ISpecimenStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specimenStatus }) => (this.specimenStatus = specimenStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
