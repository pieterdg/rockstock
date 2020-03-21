import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISpecimen } from 'app/shared/model/specimen.model';

type EntityResponseType = HttpResponse<ISpecimen>;
type EntityArrayResponseType = HttpResponse<ISpecimen[]>;

@Injectable({ providedIn: 'root' })
export class SpecimenService {
  public resourceUrl = SERVER_API_URL + 'api/specimen';

  constructor(protected http: HttpClient) {}

  create(specimen: ISpecimen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(specimen);
    return this.http
      .post<ISpecimen>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(specimen: ISpecimen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(specimen);
    return this.http
      .put<ISpecimen>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISpecimen>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISpecimen[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(specimen: ISpecimen): ISpecimen {
    const copy: ISpecimen = Object.assign({}, specimen, {
      acquiredDate: specimen.acquiredDate && specimen.acquiredDate.isValid() ? specimen.acquiredDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.acquiredDate = res.body.acquiredDate ? moment(res.body.acquiredDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((specimen: ISpecimen) => {
        specimen.acquiredDate = specimen.acquiredDate ? moment(specimen.acquiredDate) : undefined;
      });
    }
    return res;
  }
}
