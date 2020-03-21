import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

@Component({
  selector: 'jhi-nickel-strunz-level-three-detail',
  templateUrl: './nickel-strunz-level-three-detail.component.html'
})
export class NickelStrunzLevelThreeDetailComponent implements OnInit {
  nickelStrunzLevelThree: INickelStrunzLevelThree | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nickelStrunzLevelThree }) => (this.nickelStrunzLevelThree = nickelStrunzLevelThree));
  }

  previousState(): void {
    window.history.back();
  }
}
