import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { ILocation } from 'app/shared/model/location.model';
import { IPicture } from 'app/shared/model/picture.model';
import { PictureSearchService } from 'app/shared/search/picture-search.service';
import { PictureService } from 'app/entities/picture/picture.service';

@Component( {
    selector: 'jhi-location-detail',
    templateUrl: './location-detail.component.html'
} )
export class LocationDetailComponent implements OnInit {
    location: ILocation | null = null;
    pictures: IPicture[] = [];

    constructor( protected activatedRoute: ActivatedRoute, private pictureSearchService: PictureSearchService, protected pictureService: PictureService,
            private router: Router) { }

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
    
    addPicture(): void {
        this.pictureService.setLocation(this.location || {});
        this.router.navigateByUrl( '/picture/new' );      
    }

    previousState(): void {
        window.history.back();
    }
}
