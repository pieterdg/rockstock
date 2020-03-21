import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMineral, Mineral } from 'app/shared/model/mineral.model';
import { MineralService } from './mineral.service';
import { MineralComponent } from './mineral.component';
import { MineralDetailComponent } from './mineral-detail.component';
import { MineralUpdateComponent } from './mineral-update.component';

@Injectable({ providedIn: 'root' })
export class MineralResolve implements Resolve<IMineral> {
  constructor(private service: MineralService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMineral> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mineral: HttpResponse<Mineral>) => {
          if (mineral.body) {
            return of(mineral.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Mineral());
  }
}

export const mineralRoute: Routes = [
  {
    path: '',
    component: MineralComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rockstockApp.mineral.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MineralDetailComponent,
    resolve: {
      mineral: MineralResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.mineral.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MineralUpdateComponent,
    resolve: {
      mineral: MineralResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.mineral.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MineralUpdateComponent,
    resolve: {
      mineral: MineralResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.mineral.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
