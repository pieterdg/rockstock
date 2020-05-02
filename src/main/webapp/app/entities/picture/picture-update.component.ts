import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IPicture, Picture } from 'app/shared/model/picture.model';
import { PictureService } from './picture.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { ISpecimen } from 'app/shared/model/specimen.model';
import { SpecimenService } from 'app/entities/specimen/specimen.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';

type SelectableEntity = ISpecimen | ILocation;

@Component( {
    selector: 'jhi-picture-update',
    templateUrl: './picture-update.component.html'
} )
export class PictureUpdateComponent implements OnInit {
    isSaving = false;
    specimen: ISpecimen[] = [];
    locations: ILocation[] = [];

    fixedSpecimen?: ISpecimen;
    fixedLocation?: ILocation;

    editForm = this.fb.group( {
        id: [],
        data: [null, [Validators.required]],
        dataContentType: [],
        description: [],
        specimenId: [],
        locationId: []
    } );

    constructor(
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected pictureService: PictureService,
        protected specimenService: SpecimenService,
        protected locationService: LocationService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(( { picture } ) => {
            this.updateForm( picture );

            this.specimenService.query().subscribe(( res: HttpResponse<ISpecimen[]> ) => this.onSpecimenReceived( res.body || [] ) );

            this.locationService.query().subscribe(( res: HttpResponse<ILocation[]> ) => this.onLocationsReceived( res.body || [] ) );
        } );
    }

    onSpecimenReceived( specimen: ISpecimen[] ): void {
        this.specimen = specimen;
        if ( this.pictureService.getSpecimen() && this.pictureService.getSpecimen().id ) {
            this.fixedSpecimen = this.pictureService.getSpecimen();
            this.editForm.patchValue( {
                specimenId: this.fixedSpecimen ?.id,
            } );
            this.pictureService.setSpecimen( {} );
        }
    }

    onLocationsReceived( locations: ILocation[] ): void {
        this.locations = locations;
        if ( this.pictureService.getLocation() && this.pictureService.getLocation().id ) {
            this.fixedLocation = this.pictureService.getLocation();
            this.editForm.patchValue( {
                locationId: this.fixedLocation ?.id,
            } );
            this.pictureService.setLocation( {} );
        }
    }

    updateForm( picture: IPicture ): void {
        this.editForm.patchValue( {
            id: picture.id,
            data: picture.data,
            dataContentType: picture.dataContentType,
            description: picture.description,
            specimenId: picture.specimenId,
            locationId: picture.locationId
        } );
    }

    byteSize( base64String: string ): string {
        return this.dataUtils.byteSize( base64String );
    }

    openFile( contentType: string, base64String: string ): void {
        this.dataUtils.openFile( contentType, base64String );
    }

    setFileData( event: Event, field: string, isImage: boolean ): void {
        this.dataUtils.loadFileToForm( event, this.editForm, field, isImage ).subscribe( null, ( err: JhiFileLoadError ) => {
            this.eventManager.broadcast(
                new JhiEventWithContent<AlertError>( 'rockstockApp.error', { ...err, key: 'error.file.' + err.key } )
            );
        } );
    }

    clearInputImage( field: string, fieldContentType: string, idInput: string ): void {
        this.editForm.patchValue( {
            [field]: null,
            [fieldContentType]: null
        } );
        if ( this.elementRef && idInput && this.elementRef.nativeElement.querySelector( '#' + idInput ) ) {
            this.elementRef.nativeElement.querySelector( '#' + idInput ).value = null;
        }
    }

    previousState(): void {
        window.history.back();
    }

    save(): void {
        this.isSaving = true;
        const picture = this.createFromForm();

        if ( picture.specimenId === undefined && picture.locationId === undefined ) {
            console.error( "Specimen or location is needed to save the picture." );
            return;
        }

        if ( picture.id !== undefined ) {
            this.subscribeToSaveResponse( this.pictureService.update( picture ) );
        } else {
            this.subscribeToSaveResponse( this.pictureService.create( picture ) );
        }
    }

    private createFromForm(): IPicture {
        return {
            ...new Picture(),
            id: this.editForm.get( ['id'] )!.value,
            dataContentType: this.editForm.get( ['dataContentType'] )!.value,
            data: this.editForm.get( ['data'] )!.value,
            description: this.editForm.get( ['description'] )!.value,
            specimenId: this.editForm.get( ['specimenId'] )!.value,
            locationId: this.editForm.get( ['locationId'] )!.value
        };
    }

    protected subscribeToSaveResponse( result: Observable<HttpResponse<IPicture>> ): void {
        result.subscribe(
            () => this.onSaveSuccess(),
            () => this.onSaveError()
        );
    }

    protected onSaveSuccess(): void {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError(): void {
        this.isSaving = false;
    }

    trackById( index: number, item: SelectableEntity ): any {
        return item.id;
    }
}
