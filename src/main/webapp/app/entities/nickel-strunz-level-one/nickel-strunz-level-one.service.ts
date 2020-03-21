import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';

type EntityResponseType = HttpResponse<INickelStrunzLevelOne>;
type EntityArrayResponseType = HttpResponse<INickelStrunzLevelOne[]>;

@Injectable({ providedIn: 'root' })
export class NickelStrunzLevelOneService {
  public resourceUrl = SERVER_API_URL + 'api/nickel-strunz-level-ones';

  constructor(protected http: HttpClient) {}

  create(nickelStrunzLevelOne: INickelStrunzLevelOne): Observable<EntityResponseType> {
    return this.http.post<INickelStrunzLevelOne>(this.resourceUrl, nickelStrunzLevelOne, { observe: 'response' });
  }

  update(nickelStrunzLevelOne: INickelStrunzLevelOne): Observable<EntityResponseType> {
    return this.http.put<INickelStrunzLevelOne>(this.resourceUrl, nickelStrunzLevelOne, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INickelStrunzLevelOne>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INickelStrunzLevelOne[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
