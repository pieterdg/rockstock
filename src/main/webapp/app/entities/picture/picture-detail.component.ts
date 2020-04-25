import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';
import { HttpResponse } from '@angular/common/http';

import { IPicture } from 'app/shared/model/picture.model';
import { ISpecimen } from 'app/shared/model/specimen.model';
import { ILocation } from 'app/shared/model/location.model';

import { SpecimenService } from 'app/entities/specimen/specimen.service';
import { LocationService } from 'app/entities/location/location.service';

@Component( {
    selector: 'jhi-picture-detail',
    templateUrl: './picture-detail.component.html'
} )
export class PictureDetailComponent implements OnInit {
    picture: IPicture | null = null;
    specimen: ISpecimen | null = null;
    location: ILocation | null = null;

    constructor( protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute, protected specimenService: SpecimenService, protected locationService: LocationService ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(( { picture } ) => this.onPictureReceived( picture ) );
    }

    onPictureReceived( picture: IPicture ): void {
        this.picture = picture;

        // Lookup more information for the picture:
        if ( this.picture ?.specimenId) {
            this.specimenService.find( this.picture ?.specimenId).subscribe(( res: HttpResponse<ISpecimen> ) => ( this.specimen = res.body || null ) );
        }

        if ( this.picture ?.locationId) {
            this.locationService.find( this.picture ?.locationId).subscribe(( res: HttpResponse<ILocation> ) => ( this.location = res.body || null ) );
        }
    }

    byteSize( base64String: string ): string {
        return this.dataUtils.byteSize( base64String );
    }

    openFile( contentType: string, base64String: string ): void {
        this.dataUtils.openFile( contentType, base64String );
    }

    previousState(): void {
        window.history.back();
    }
}
