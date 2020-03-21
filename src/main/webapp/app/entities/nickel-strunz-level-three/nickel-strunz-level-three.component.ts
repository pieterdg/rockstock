import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NickelStrunzLevelThreeService } from './nickel-strunz-level-three.service';
import { NickelStrunzLevelThreeDeleteDialogComponent } from './nickel-strunz-level-three-delete-dialog.component';

@Component({
  selector: 'jhi-nickel-strunz-level-three',
  templateUrl: './nickel-strunz-level-three.component.html'
})
export class NickelStrunzLevelThreeComponent implements OnInit, OnDestroy {
  nickelStrunzLevelThrees?: INickelStrunzLevelThree[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected nickelStrunzLevelThreeService: NickelStrunzLevelThreeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.nickelStrunzLevelThreeService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<INickelStrunzLevelThree[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInNickelStrunzLevelThrees();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INickelStrunzLevelThree): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNickelStrunzLevelThrees(): void {
    this.eventSubscriber = this.eventManager.subscribe('nickelStrunzLevelThreeListModification', () => this.loadPage());
  }

  delete(nickelStrunzLevelThree: INickelStrunzLevelThree): void {
    const modalRef = this.modalService.open(NickelStrunzLevelThreeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nickelStrunzLevelThree = nickelStrunzLevelThree;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: INickelStrunzLevelThree[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/nickel-strunz-level-three'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.nickelStrunzLevelThrees = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
