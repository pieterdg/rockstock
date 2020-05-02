import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPicture } from 'app/shared/model/picture.model';
import { ISpecimen } from 'app/shared/model/specimen.model';
import { ILocation } from 'app/shared/model/location.model';

type EntityResponseType = HttpResponse<IPicture>;
type EntityArrayResponseType = HttpResponse<IPicture[]>;

@Injectable({ providedIn: 'root' })
export class PictureService {
  public resourceUrl = SERVER_API_URL + 'api/pictures';
  
  specimen?: ISpecimen;
  location?: ILocation;

  constructor(protected http: HttpClient) {}

  create(picture: IPicture): Observable<EntityResponseType> {
    return this.http.post<IPicture>(this.resourceUrl, picture, { observe: 'response' });
  }

  update(picture: IPicture): Observable<EntityResponseType> {
    return this.http.put<IPicture>(this.resourceUrl, picture, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPicture>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPicture[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  
  setSpecimen(specimen: ISpecimen): void {
      this.specimen = specimen;
  }
  
  getSpecimen(): ISpecimen {
      return this.specimen || {};
  }
  
  setLocation(location: ILocation): void {
      this.location = location;
  }
  
  getLocation(): ILocation {
      return this.location || {};
  }
}
