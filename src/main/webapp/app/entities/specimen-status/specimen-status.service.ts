import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISpecimenStatus } from 'app/shared/model/specimen-status.model';

type EntityResponseType = HttpResponse<ISpecimenStatus>;
type EntityArrayResponseType = HttpResponse<ISpecimenStatus[]>;

@Injectable({ providedIn: 'root' })
export class SpecimenStatusService {
  public resourceUrl = SERVER_API_URL + 'api/specimen-statuses';

  constructor(protected http: HttpClient) {}

  create(specimenStatus: ISpecimenStatus): Observable<EntityResponseType> {
    return this.http.post<ISpecimenStatus>(this.resourceUrl, specimenStatus, { observe: 'response' });
  }

  update(specimenStatus: ISpecimenStatus): Observable<EntityResponseType> {
    return this.http.put<ISpecimenStatus>(this.resourceUrl, specimenStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISpecimenStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISpecimenStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
