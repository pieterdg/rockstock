import { Component, OnInit } from '@angular/core';
import { HomeSummaryService } from './home-summary.service';
import { HttpResponse } from '@angular/common/http';

@Component( {
    selector: 'jhi-home-summary',
    templateUrl: './home-summary.component.html'
} )

export class HomeSummaryComponent implements OnInit {
    nrSpecimen: Number;
    nrMinerals: Number;
    nrLocations: Number;

    constructor(private homeSummaryService: HomeSummaryService) {
        this.nrSpecimen = 0;
        this.nrMinerals = 0;
        this.nrLocations = 0;
    }

    ngOnInit(): void {
        // Fetch nr locations:
        this.homeSummaryService.countLocations().subscribe(
                ( res: HttpResponse<Number> ) => this.onGetLocationsSuccess( res.body || 0 ) );

        // Fetch nr minerals:
        this.homeSummaryService.countMinerals().subscribe(
                ( res: HttpResponse<Number> ) => this.onGetMineralsSuccess( res.body || 0 ) );

        // Fetch nr specimen:
        this.homeSummaryService.countSpecimen().subscribe(
                ( res: HttpResponse<Number> ) => this.onGetSpecimenSuccess( res.body || 0 ) );
    }

    protected onGetSpecimenSuccess( nrSpecimen: Number ): void {
        this.nrSpecimen = nrSpecimen;
    }

    protected onGetMineralsSuccess( nrMinerals: Number ): void {
        this.nrMinerals = nrMinerals;
    }

    protected onGetLocationsSuccess( nrLocations: Number ): void {
        this.nrLocations = nrLocations;
    }
}