import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { INickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';
import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

import { SERVER_API_URL } from 'app/app.constants';

@Injectable( { providedIn: 'root' } )
export class SearchService {

    public resourceUrl = SERVER_API_URL + 'api/search';

    constructor( protected http: HttpClient ) { }

    getNickelStrunzLevelTwosForLevelOne( parentId: number ): Observable<HttpResponse<INickelStrunzLevelTwo[]>> {
        return this.http.get<INickelStrunzLevelTwo[]>( `${this.resourceUrl}/nickel-strunz-level-twos/` + parentId, { observe: 'response' } );
    }

    getNickelStrunzLevelThreesForLevelTwo( parentId: number ): Observable<HttpResponse<INickelStrunzLevelThree[]>> {
        return this.http.get<INickelStrunzLevelThree[]>( `${this.resourceUrl}/nickel-strunz-level-threes/` + parentId, { observe: 'response' } );
    }

    getHierarchy( nickelstrunzLevelThreeId: number ): Observable<HttpResponse<any>> {
        return this.http.get<any>( `${this.resourceUrl}/nickel-strunz-hierarchy/` + nickelstrunzLevelThreeId, { observe: 'response' } );
    }
}
