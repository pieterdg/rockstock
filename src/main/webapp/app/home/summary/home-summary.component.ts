import { Component, OnInit } from '@angular/core';
import { HomeSummaryService } from './home-summary.service';
import { /*HttpErrorResponse,*/ HttpResponse } from '@angular/common/http';

@Component( {
    selector: 'jhi-home-summary',
    templateUrl: './home-summary.component.html'
} )

export class HomeSummaryComponent implements OnInit {
    nrSpecimen: Number;
    nrMinerals: Number;
    nrCountries: Number;

    constructor(private homeSummaryService: HomeSummaryService) {
        this.nrSpecimen = 0;
        this.nrMinerals = 0;
        this.nrCountries = 0;
    }

    ngOnInit(): void {
        // Fetch nr countries:
        this.homeSummaryService.countCountries().subscribe(
                ( res: HttpResponse<Number> ) => this.onGetCountriesSuccess( res.body ) );

        // Fetch nr minerals:
        this.homeSummaryService.countMinerals().subscribe(
                ( res: HttpResponse<Number> ) => this.onGetMineralsSuccess( res.body ) );

        // Fetch nr specimen:
        this.homeSummaryService.countSpecimen().subscribe(
                ( res: HttpResponse<Number> ) => this.onGetSpecimenSuccess( res.body ) );
    }

    protected onGetSpecimenSuccess( nrSpecimen: Number ): void {
        this.nrSpecimen = nrSpecimen;
    }

    protected onGetMineralsSuccess( nrMinerals: Number ): void {
        this.nrMinerals = nrMinerals;
    }

    protected onGetCountriesSuccess( nrCountries: Number ): void {
        this.nrCountries = nrCountries;
    }
}