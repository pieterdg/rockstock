import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INickelStrunzLevelTwo, NickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';
import { NickelStrunzLevelTwoService } from './nickel-strunz-level-two.service';
import { NickelStrunzLevelTwoComponent } from './nickel-strunz-level-two.component';
import { NickelStrunzLevelTwoDetailComponent } from './nickel-strunz-level-two-detail.component';
import { NickelStrunzLevelTwoUpdateComponent } from './nickel-strunz-level-two-update.component';

@Injectable({ providedIn: 'root' })
export class NickelStrunzLevelTwoResolve implements Resolve<INickelStrunzLevelTwo> {
  constructor(private service: NickelStrunzLevelTwoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INickelStrunzLevelTwo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nickelStrunzLevelTwo: HttpResponse<NickelStrunzLevelTwo>) => {
          if (nickelStrunzLevelTwo.body) {
            return of(nickelStrunzLevelTwo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NickelStrunzLevelTwo());
  }
}

export const nickelStrunzLevelTwoRoute: Routes = [
  {
    path: '',
    component: NickelStrunzLevelTwoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rockstockApp.nickelStrunzLevelTwo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NickelStrunzLevelTwoDetailComponent,
    resolve: {
      nickelStrunzLevelTwo: NickelStrunzLevelTwoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelTwo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NickelStrunzLevelTwoUpdateComponent,
    resolve: {
      nickelStrunzLevelTwo: NickelStrunzLevelTwoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelTwo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NickelStrunzLevelTwoUpdateComponent,
    resolve: {
      nickelStrunzLevelTwo: NickelStrunzLevelTwoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelTwo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
