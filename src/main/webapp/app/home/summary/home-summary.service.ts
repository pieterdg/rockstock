import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';

@Injectable( { providedIn: 'root' } )
export class HomeSummaryService {

    public resourceUrl = SERVER_API_URL + 'api';

    constructor( protected http: HttpClient ) { }

    countCountries(): Observable<HttpResponse<number>> {
        return this.http.get<number>( `${this.resourceUrl}/countries/count`, { observe: 'response' } );
    }

    countMinerals(): Observable<HttpResponse<number>> {
        return this.http.get<number>( `${this.resourceUrl}/minerals/count`, { observe: 'response' } );
    }

    countSpecimen(): Observable<HttpResponse<number>> {
        return this.http.get<number>( `${this.resourceUrl}/specimen/count`, { observe: 'response' } );
    }
}
