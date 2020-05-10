import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStorageLocation, StorageLocation } from 'app/shared/model/storage-location.model';
import { StorageLocationService } from './storage-location.service';
import { StorageLocationComponent } from './storage-location.component';
import { StorageLocationDetailComponent } from './storage-location-detail.component';
import { StorageLocationUpdateComponent } from './storage-location-update.component';

@Injectable({ providedIn: 'root' })
export class StorageLocationResolve implements Resolve<IStorageLocation> {
  constructor(private service: StorageLocationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStorageLocation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((storageLocation: HttpResponse<StorageLocation>) => {
          if (storageLocation.body) {
            return of(storageLocation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StorageLocation());
  }
}

export const storageLocationRoute: Routes = [
  {
    path: '',
    component: StorageLocationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rockstockApp.storageLocation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StorageLocationDetailComponent,
    resolve: {
      storageLocation: StorageLocationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.storageLocation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StorageLocationUpdateComponent,
    resolve: {
      storageLocation: StorageLocationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.storageLocation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StorageLocationUpdateComponent,
    resolve: {
      storageLocation: StorageLocationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rockstockApp.storageLocation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
