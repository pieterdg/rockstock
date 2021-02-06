import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IFileImport, IImportMessage } from 'app/shared/model/tools/import.model';

@Injectable( { providedIn: 'root' } )
export class RockToolsService {
    
    public resourceUrl = SERVER_API_URL + 'api/import';

    constructor( private http: HttpClient ) { }

    import( fileImport: IFileImport ): Observable<HttpResponse<IImportMessage>> {
        return this.http.post<IImportMessage>( this.resourceUrl, fileImport, { observe: 'response' } );
    }
}
