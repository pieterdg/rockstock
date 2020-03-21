import { Component, OnInit } from '@angular/core';
import { HomeSummaryService } from './home-summary.service';
//import { Observable } from 'rxjs';
//import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component( {
    selector: 'jhi-home-summary',
    templateUrl: './home-summary.component.html'
} )
export class HomeSummaryComponent implements OnInit {
    nrSpecimen: number;
    nrMinerals: number;
    nrCountries: number;

    constructor(
        private homeSummaryService: HomeSummaryService
    ) {
        this.nrSpecimen = 0;
        this.nrMinerals = 0;
        this.nrCountries = 0;
    }

    ngOnInit() { }

    _ngOnInit() {

        // Fetch nr specimen:
        //        this.homeSummaryService.countCountries().subscribe( resp => {
        //        } );

        //        this.homeSummaryService.countCountries().subscribe(
        //                ( res: HttpResponse<number> ) => this.onGetCountriesSuccess( res.body ), ( res: HttpErrorResponse ) => this.onGetCountriesError() );
        //
        //        // Fetch nr minerals:
        //        this.homeSummaryService.countMinerals().subscribe(
        //            ( res: HttpResponse<number> ) => this.onGetMineralsSuccess( res.body ), ( res: HttpErrorResponse ) => this.onGetMineralsError() );
        //
        //        // Fetch nr countries:
        //        this.homeSummaryService.countSpecimen().subscribe(
        //                ( res: HttpResponse<number> ) => this.onGetSpecimenSuccess( res.body ), ( res: HttpErrorResponse ) => this.onGetSpecimenError() );
    }

    protected onGetSpecimenSuccess( nrSpecimen: number ): void {
        this.nrSpecimen = nrSpecimen;
    }

    protected onGetSpecimenError(): void {
        this.nrSpecimen = -1;
    }

    protected onGetMineralsSuccess( nrMinerals: number ): void {
        this.nrMinerals = nrMinerals;
    }

    protected onGetMineralsError(): void {
        this.nrMinerals = -1;
    }

    protected onGetCountriesSuccess( nrCountries: number ): void {
        this.nrCountries = nrCountries;
    }

    protected onGetCountriesError(): void {
        this.nrCountries = -1;
    }
}