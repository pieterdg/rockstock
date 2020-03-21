import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INickelStrunzLevelOne, NickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';
import { NickelStrunzLevelOneService } from './nickel-strunz-level-one.service';
import { NickelStrunzLevelOneComponent } from './nickel-strunz-level-one.component';
import { NickelStrunzLevelOneDetailComponent } from './nickel-strunz-level-one-detail.component';
import { NickelStrunzLevelOneUpdateComponent } from './nickel-strunz-level-one-update.component';

@Injectable({ providedIn: 'root' })
export class NickelStrunzLevelOneResolve implements Resolve<INickelStrunzLevelOne> {
  constructor(private service: NickelStrunzLevelOneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INickelStrunzLevelOne> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nickelStrunzLevelOne: HttpResponse<NickelStrunzLevelOne>) => {
          if (nickelStrunzLevelOne.body) {
            return of(nickelStrunzLevelOne.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NickelStrunzLevelOne());
  }
}

export const nickelStrunzLevelOneRoute: Routes = [
  {
    path: '',
    component: NickelStrunzLevelOneComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rockstockApp.nickelStrunzLevelOne.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NickelStrunzLevelOneDetailComponent,
    resolve: {
      nickelStrunzLevelOne: NickelStrunzLevelOneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelOne.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NickelStrunzLevelOneUpdateComponent,
    resolve: {
      nickelStrunzLevelOne: NickelStrunzLevelOneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelOne.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NickelStrunzLevelOneUpdateComponent,
    resolve: {
      nickelStrunzLevelOne: NickelStrunzLevelOneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelOne.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
