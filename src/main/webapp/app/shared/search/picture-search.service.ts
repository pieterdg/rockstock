import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPicture } from 'app/shared/model/picture.model';

import { SERVER_API_URL } from 'app/app.constants';

@Injectable( { providedIn: 'root' } )
export class PictureSearchService {

    public resourceUrl = SERVER_API_URL + 'api/picturesearch';

    constructor( protected http: HttpClient ) { }

    // ----------------------------------------------------------------------------------------------------------------
    // Picture retrieval methods:
    // ----------------------------------------------------------------------------------------------------------------

    getPicturesForSpecimen( specimenId: number ): Observable<HttpResponse<IPicture[]>> {
        return this.http.get<IPicture[]>( `${this.resourceUrl}/specimen/` + specimenId, { observe: 'response' } );
    }

    getThumbnailsForSpecimen( specimenId: number, maxHeight: number, maxCount: number ): Observable<HttpResponse<IPicture[]>> {
        return this.http.get<IPicture[]>( `${this.resourceUrl}/specimen/` + specimenId + '?maxHeight=' + maxHeight + '&maxCount=' + maxCount, { observe: 'response' } );
    }

    getPicturesForLocation( locationId: number ): Observable<HttpResponse<IPicture[]>> {
        return this.http.get<IPicture[]>( `${this.resourceUrl}/location/` + locationId, { observe: 'response' } );
    }

    getThumbnailsForLocation( locationId: number, maxHeight: number, maxCount: number ): Observable<HttpResponse<IPicture[]>> {
        return this.http.get<IPicture[]>( `${this.resourceUrl}/location/` + locationId + '?maxHeight=' + maxHeight + '&maxCount=' + maxCount, { observe: 'response' } );
    }

    getPicturesForMineral( mineralId: number ): Observable<HttpResponse<IPicture[]>> {
        return this.http.get<IPicture[]>( `${this.resourceUrl}/mineral/` + mineralId, { observe: 'response' } );
    }

    getThumbnailsForMineral( mineralId: number, maxHeight: number, maxCount: number ): Observable<HttpResponse<IPicture[]>> {
        return this.http.get<IPicture[]>( `${this.resourceUrl}/mineral/` + mineralId + '?maxHeight=' + maxHeight + '&maxCount=' + maxCount, { observe: 'response' } );
    }

    getRandomPicture(): Observable<HttpResponse<IPicture>> {
        return this.http.get<IPicture>( `${this.resourceUrl}/random`, { observe: 'response' } );
    }
}
