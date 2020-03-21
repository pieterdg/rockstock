import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INickelStrunzLevelThree, NickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';
import { NickelStrunzLevelThreeService } from './nickel-strunz-level-three.service';
import { NickelStrunzLevelThreeComponent } from './nickel-strunz-level-three.component';
import { NickelStrunzLevelThreeDetailComponent } from './nickel-strunz-level-three-detail.component';
import { NickelStrunzLevelThreeUpdateComponent } from './nickel-strunz-level-three-update.component';

@Injectable({ providedIn: 'root' })
export class NickelStrunzLevelThreeResolve implements Resolve<INickelStrunzLevelThree> {
  constructor(private service: NickelStrunzLevelThreeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INickelStrunzLevelThree> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nickelStrunzLevelThree: HttpResponse<NickelStrunzLevelThree>) => {
          if (nickelStrunzLevelThree.body) {
            return of(nickelStrunzLevelThree.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NickelStrunzLevelThree());
  }
}

export const nickelStrunzLevelThreeRoute: Routes = [
  {
    path: '',
    component: NickelStrunzLevelThreeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rockstockApp.nickelStrunzLevelThree.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NickelStrunzLevelThreeDetailComponent,
    resolve: {
      nickelStrunzLevelThree: NickelStrunzLevelThreeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelThree.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NickelStrunzLevelThreeUpdateComponent,
    resolve: {
      nickelStrunzLevelThree: NickelStrunzLevelThreeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelThree.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NickelStrunzLevelThreeUpdateComponent,
    resolve: {
      nickelStrunzLevelThree: NickelStrunzLevelThreeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rockstockApp.nickelStrunzLevelThree.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
