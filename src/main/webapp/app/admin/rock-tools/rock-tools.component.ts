import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';
import { AlertError } from 'app/shared/alert/alert-error.model';
// import { Observable } from 'rxjs';

import { RockToolsService } from './rock-tools.service';
import { IFileImport, FileImport, IImportMessage } from 'app/shared/model/tools/import.model';

@Component( {
    selector: 'jhi-rock-tools',
    templateUrl: './rock-tools.component.html'
} )
export class RockToolsComponent implements OnInit {
    isSaving = false;
    saveText = "";

    importForm = this.fb.group( {
        id: [],
        data: [null, [Validators.required]],
        dataContentType: [],
        importLocations: true,
        importSpecimen: true
    } );

    constructor( private rockToolsService: RockToolsService, private fb: FormBuilder, protected dataUtils: JhiDataUtils, protected eventManager: JhiEventManager, ) { }

    ngOnInit(): void {
    }

    openFile( contentType: string, base64String: string ): void {
        this.dataUtils.openFile( contentType, base64String );
    }

    setFileData( event: Event, field: string, isImage: boolean ): void {
        this.dataUtils.loadFileToForm( event, this.importForm, field, isImage ).subscribe( null, ( err: JhiFileLoadError ) => {
            this.eventManager.broadcast(
                new JhiEventWithContent<AlertError>( 'rockstockApp.error', { ...err, key: 'error.file.' + err.key } )
            );
        } );
    }

    importFile(): void {
        this.isSaving = true;
        this.saveText = "Uploading file...";
        const fileImport = this.createFromForm();
        console.error( "FileImport: locations? " + this.importForm.get( ['importLocations'] )!.value );
        console.error( "FileImport: specimen? " + this.importForm.get( ['importSpecimen'] )!.value );
        if ( fileImport.data === undefined ) {
            console.error( "CSV file required to import." );
            return;
        }

        this.rockToolsService.import( fileImport ).subscribe(
            ( result: HttpResponse<IImportMessage> ) => this.onImportSuccess( result.body || {} ), ( result: HttpResponse<IImportMessage> ) => this.onImportError( result.body || {} ) );
    }

    private createFromForm(): IFileImport {
        return {
            ...new FileImport(),
            dataContentType: this.importForm.get( ['dataContentType'] )!.value,
            data: this.importForm.get( ['data'] )!.value,
            importLocations: this.importForm.get( ['importLocations'] )!.value,
            importSpecimen: this.importForm.get( ['importSpecimen'] )!.value
        };
    }

    protected onImportSuccess( msg: IImportMessage ): void {
        console.error( "Import succes " );
        this.isSaving = false;
        if ( msg.message != null ) {
            this.saveText = msg ?.message;
        }
    }

    protected onImportError( msg: IImportMessage ): void {
        console.error( "Import error " );
        console.error( msg );
        this.isSaving = false;
        if ( msg.message != null ) {
            this.saveText = msg ?.message;
        }
    }
}
