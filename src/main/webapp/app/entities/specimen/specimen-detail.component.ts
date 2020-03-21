import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecimen } from 'app/shared/model/specimen.model';

@Component({
  selector: 'jhi-specimen-detail',
  templateUrl: './specimen-detail.component.html'
})
export class SpecimenDetailComponent implements OnInit {
  specimen: ISpecimen | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specimen }) => (this.specimen = specimen));
  }

  previousState(): void {
    window.history.back();
  }
}
