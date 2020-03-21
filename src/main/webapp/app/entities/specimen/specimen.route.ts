import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISpecimen, Specimen } from 'app/shared/model/specimen.model';
import { SpecimenService } from './specimen.service';
import { SpecimenComponent } from './specimen.component';
import { SpecimenDetailComponent } from './specimen-detail.component';
import { SpecimenUpdateComponent } from './specimen-update.component';

@Injectable({ providedIn: 'root' })
export class SpecimenResolve implements Resolve<ISpecimen> {
  constructor(private service: SpecimenService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISpecimen> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((specimen: HttpResponse<Specimen>) => {
          if (specimen.body) {
            return of(specimen.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Specimen());
  }
}

export const specimenRoute: Routes = [
  {
    path: '',
    component: SpecimenComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rockstockApp.specimen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SpecimenDetailComponent,
    resolve: {
      specimen: SpecimenResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.specimen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SpecimenUpdateComponent,
    resolve: {
      specimen: SpecimenResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.specimen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SpecimenUpdateComponent,
    resolve: {
      specimen: SpecimenResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.specimen.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
