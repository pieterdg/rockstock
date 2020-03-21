import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

type EntityResponseType = HttpResponse<INickelStrunzLevelThree>;
type EntityArrayResponseType = HttpResponse<INickelStrunzLevelThree[]>;

@Injectable({ providedIn: 'root' })
export class NickelStrunzLevelThreeService {
  public resourceUrl = SERVER_API_URL + 'api/nickel-strunz-level-threes';

  constructor(protected http: HttpClient) {}

  create(nickelStrunzLevelThree: INickelStrunzLevelThree): Observable<EntityResponseType> {
    return this.http.post<INickelStrunzLevelThree>(this.resourceUrl, nickelStrunzLevelThree, { observe: 'response' });
  }

  update(nickelStrunzLevelThree: INickelStrunzLevelThree): Observable<EntityResponseType> {
    return this.http.put<INickelStrunzLevelThree>(this.resourceUrl, nickelStrunzLevelThree, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INickelStrunzLevelThree>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INickelStrunzLevelThree[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
