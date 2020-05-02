import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

import { ILocation } from 'app/shared/model/location.model';
import { IPicture } from 'app/shared/model/picture.model';
import { PictureSearchService } from 'app/shared/search/picture-search.service';

@Component( {
    selector: 'jhi-location-detail',
    templateUrl: './location-detail.component.html'
} )
export class LocationDetailComponent implements OnInit {
    location: ILocation | null = null;
    pictures: IPicture[] = [];

    constructor( protected activatedRoute: ActivatedRoute, private pictureSearchService: PictureSearchService ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(( { location } ) => this.onLocationReceived( location ) );
    }

    private onLocationReceived( location: ILocation ): void {
        this.location = location;
        if ( location ?.id ) {
            this.pictureSearchService.getThumbnailsForLocation( location ?.id, 250, 0 ).subscribe(( res: HttpResponse<IPicture[]> ) => this.onPicturesReceived( res.body || [] ) );
        }
    }

    private onPicturesReceived( pictures: IPicture[] ): void {
        this.pictures = pictures;
    }

    previousState(): void {
        window.history.back();
    }
}
