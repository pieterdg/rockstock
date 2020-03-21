import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NickelStrunzLevelTwoService } from './nickel-strunz-level-two.service';
import { NickelStrunzLevelTwoDeleteDialogComponent } from './nickel-strunz-level-two-delete-dialog.component';

@Component({
  selector: 'jhi-nickel-strunz-level-two',
  templateUrl: './nickel-strunz-level-two.component.html'
})
export class NickelStrunzLevelTwoComponent implements OnInit, OnDestroy {
  nickelStrunzLevelTwos?: INickelStrunzLevelTwo[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected nickelStrunzLevelTwoService: NickelStrunzLevelTwoService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.nickelStrunzLevelTwoService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<INickelStrunzLevelTwo[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInNickelStrunzLevelTwos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INickelStrunzLevelTwo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNickelStrunzLevelTwos(): void {
    this.eventSubscriber = this.eventManager.subscribe('nickelStrunzLevelTwoListModification', () => this.loadPage());
  }

  delete(nickelStrunzLevelTwo: INickelStrunzLevelTwo): void {
    const modalRef = this.modalService.open(NickelStrunzLevelTwoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nickelStrunzLevelTwo = nickelStrunzLevelTwo;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: INickelStrunzLevelTwo[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/nickel-strunz-level-two'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.nickelStrunzLevelTwos = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
