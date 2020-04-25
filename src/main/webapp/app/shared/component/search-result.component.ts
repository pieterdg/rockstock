import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecimen } from 'app/shared/model/specimen.model';
import { ILocation } from 'app/shared/model/location.model';
import { IMineral } from 'app/shared/model/mineral.model';


@Component( {
    selector: 'jhi-search-result',
    templateUrl: './search-result.component.html'
} )

export class SearchResultComponent implements OnInit {

    @Input() specimen?: ISpecimen;
    @Input() location?: ILocation;
    @Input() mineral?: IMineral;

    constructor( protected activatedRoute: ActivatedRoute ) { }

    ngOnInit(): void {
    }
}