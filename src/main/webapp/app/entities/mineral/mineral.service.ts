import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMineral } from 'app/shared/model/mineral.model';

type EntityResponseType = HttpResponse<IMineral>;
type EntityArrayResponseType = HttpResponse<IMineral[]>;

@Injectable({ providedIn: 'root' })
export class MineralService {
  public resourceUrl = SERVER_API_URL + 'api/minerals';

  constructor(protected http: HttpClient) {}

  create(mineral: IMineral): Observable<EntityResponseType> {
    return this.http.post<IMineral>(this.resourceUrl, mineral, { observe: 'response' });
  }

  update(mineral: IMineral): Observable<EntityResponseType> {
    return this.http.put<IMineral>(this.resourceUrl, mineral, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMineral>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMineral[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
