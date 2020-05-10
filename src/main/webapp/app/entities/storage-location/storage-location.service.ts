import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStorageLocation } from 'app/shared/model/storage-location.model';

type EntityResponseType = HttpResponse<IStorageLocation>;
type EntityArrayResponseType = HttpResponse<IStorageLocation[]>;

@Injectable({ providedIn: 'root' })
export class StorageLocationService {
  public resourceUrl = SERVER_API_URL + 'api/storage-locations';

  constructor(protected http: HttpClient) {}

  create(storageLocation: IStorageLocation): Observable<EntityResponseType> {
    return this.http.post<IStorageLocation>(this.resourceUrl, storageLocation, { observe: 'response' });
  }

  update(storageLocation: IStorageLocation): Observable<EntityResponseType> {
    return this.http.put<IStorageLocation>(this.resourceUrl, storageLocation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStorageLocation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStorageLocation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
