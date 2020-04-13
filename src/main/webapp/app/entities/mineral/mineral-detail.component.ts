import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

import { SearchService} from 'app/shared/search/search.service';

import { IMineral } from 'app/shared/model/mineral.model';
import { INickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';
import { INickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';
import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

@Component({
  selector: 'jhi-mineral-detail',
  templateUrl: './mineral-detail.component.html'
})
export class MineralDetailComponent implements OnInit {
  mineral: IMineral | null = null;
  nsl1: INickelStrunzLevelOne = {};
  nsl2: INickelStrunzLevelTwo = {};
  nsl3: INickelStrunzLevelThree = {};
  nsCode: String = '';

  constructor(protected activatedRoute: ActivatedRoute, protected searchService: SearchService) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mineral }) => this.onMineralKnown(mineral));
  }

  private onMineralKnown(mineral: IMineral): void {
      this.mineral = mineral;
      if (mineral.nickelStrunzLevelThreeId != null) {
          this.searchService.getHierarchy(mineral.nickelStrunzLevelThreeId || -1).subscribe((res:HttpResponse<any>) => this.onHierarchyReceived(res.body || {}));
      }
  }
  
  private onHierarchyReceived(nsHierarchy: any): void {
      this.nsl1 = nsHierarchy.levelOne;
      this.nsl2 = nsHierarchy.levelTwo;
      this.nsl3 = nsHierarchy.levelThree;
      this.nsCode = this.nsl1.code + '.' + this.nsl2.code + this.nsl3.code + '.' + this.mineral!.nickelStruntzLevelFour;
      console.error(this.nsCode);
  }

  previousState(): void {
    window.history.back();
  }
}
