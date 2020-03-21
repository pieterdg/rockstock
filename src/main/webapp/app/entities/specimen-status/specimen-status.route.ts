import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISpecimenStatus, SpecimenStatus } from 'app/shared/model/specimen-status.model';
import { SpecimenStatusService } from './specimen-status.service';
import { SpecimenStatusComponent } from './specimen-status.component';
import { SpecimenStatusDetailComponent } from './specimen-status-detail.component';
import { SpecimenStatusUpdateComponent } from './specimen-status-update.component';

@Injectable({ providedIn: 'root' })
export class SpecimenStatusResolve implements Resolve<ISpecimenStatus> {
  constructor(private service: SpecimenStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISpecimenStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((specimenStatus: HttpResponse<SpecimenStatus>) => {
          if (specimenStatus.body) {
            return of(specimenStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SpecimenStatus());
  }
}

export const specimenStatusRoute: Routes = [
  {
    path: '',
    component: SpecimenStatusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rockstockApp.specimenStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SpecimenStatusDetailComponent,
    resolve: {
      specimenStatus: SpecimenStatusResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.specimenStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SpecimenStatusUpdateComponent,
    resolve: {
      specimenStatus: SpecimenStatusResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.specimenStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SpecimenStatusUpdateComponent,
    resolve: {
      specimenStatus: SpecimenStatusResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.specimenStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
