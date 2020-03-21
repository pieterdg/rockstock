import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';

@Component({
  selector: 'jhi-nickel-strunz-level-two-detail',
  templateUrl: './nickel-strunz-level-two-detail.component.html'
})
export class NickelStrunzLevelTwoDetailComponent implements OnInit {
  nickelStrunzLevelTwo: INickelStrunzLevelTwo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nickelStrunzLevelTwo }) => (this.nickelStrunzLevelTwo = nickelStrunzLevelTwo));
  }

  previousState(): void {
    window.history.back();
  }
}
