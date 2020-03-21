import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NickelStrunzLevelOneService } from './nickel-strunz-level-one.service';
import { NickelStrunzLevelOneDeleteDialogComponent } from './nickel-strunz-level-one-delete-dialog.component';

@Component({
  selector: 'jhi-nickel-strunz-level-one',
  templateUrl: './nickel-strunz-level-one.component.html'
})
export class NickelStrunzLevelOneComponent implements OnInit, OnDestroy {
  nickelStrunzLevelOnes?: INickelStrunzLevelOne[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected nickelStrunzLevelOneService: NickelStrunzLevelOneService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.nickelStrunzLevelOneService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<INickelStrunzLevelOne[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInNickelStrunzLevelOnes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INickelStrunzLevelOne): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNickelStrunzLevelOnes(): void {
    this.eventSubscriber = this.eventManager.subscribe('nickelStrunzLevelOneListModification', () => this.loadPage());
  }

  delete(nickelStrunzLevelOne: INickelStrunzLevelOne): void {
    const modalRef = this.modalService.open(NickelStrunzLevelOneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nickelStrunzLevelOne = nickelStrunzLevelOne;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: INickelStrunzLevelOne[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/nickel-strunz-level-one'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.nickelStrunzLevelOnes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
