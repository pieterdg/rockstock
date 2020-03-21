import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';

@Component({
  selector: 'jhi-nickel-strunz-level-one-detail',
  templateUrl: './nickel-strunz-level-one-detail.component.html'
})
export class NickelStrunzLevelOneDetailComponent implements OnInit {
  nickelStrunzLevelOne: INickelStrunzLevelOne | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nickelStrunzLevelOne }) => (this.nickelStrunzLevelOne = nickelStrunzLevelOne));
  }

  previousState(): void {
    window.history.back();
  }
}
