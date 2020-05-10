import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { FormControl } from '@angular/forms';

import { ISpecimen, Specimen } from 'app/shared/model/specimen.model';
import { SpecimenService } from './specimen.service';
import { ISpecimenStatus } from 'app/shared/model/specimen-status.model';
import { SpecimenStatusService } from 'app/entities/specimen-status/specimen-status.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { IMineral } from 'app/shared/model/mineral.model';
import { MineralService } from 'app/entities/mineral/mineral.service';
import { IStorageLocation } from 'app/shared/model/storage-location.model';
import { StorageLocationService } from 'app/entities/storage-location/storage-location.service';

type SelectableEntity = ISpecimenStatus | ILocation | IMineral;

@Component( {
    selector: 'jhi-specimen-update',
    templateUrl: './specimen-update.component.html'
} )
export class SpecimenUpdateComponent implements OnInit {
    isSaving = false;
    specimenstatuses: ISpecimenStatus[] = [];
    locations: ILocation[] = [];
    minerals: IMineral[] = [];
    storageLocations: IStorageLocation[] = [];
    acquiredDateDp: any;

    mineralFilter = new FormControl();
    filteredMinerals?: Observable<IMineral[]>;
    hasValidMineral = false;

    editForm = this.fb.group( {
        id: [],
        code: [null, [Validators.required]],
        acquiredDate: [],
        acquiredAt: [],
        acquiredPrice: [],
        acquiredBy: [],
        acquiredFrom: [],
        remark: [],
        fluorescent: [],
        statusId: [],
        locationId: [],
        storageLocationId: [],
        minerals: []
    } );

    constructor(
        protected specimenService: SpecimenService,
        protected specimenStatusService: SpecimenStatusService,
        protected locationService: LocationService,
        protected mineralService: MineralService,
        protected storageLocationService: StorageLocationService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(( { specimen } ) => {
            this.updateForm( specimen );

            this.specimenStatusService.query().subscribe(( res: HttpResponse<ISpecimenStatus[]> ) => this.onSpecimenStatussesReceived( res.body || [] ) );
            this.locationService.query().subscribe(( res: HttpResponse<ILocation[]> ) => ( this.locations = res.body || [] ) );
            this.mineralService.query().subscribe(( res: HttpResponse<IMineral[]> ) => this.onMineralsReceived( res.body || [] ) );
            this.storageLocationService.query().subscribe(( res: HttpResponse<IStorageLocation[]> ) => ( this.storageLocations = res.body || [] ) );
        } );
    }
    
    private onMineralFilterChanged(): void {
        console.error("onMineralFilterChanged");
    }

    private onSpecimenStatussesReceived( result: ISpecimenStatus[] ): void {
        this.specimenstatuses = result;
        this.editForm.patchValue( { statusId: 1 } );
    }
    
    private onMineralsReceived( result: IMineral[] ): void {
        this.minerals = result;

        // Listen to changes in the mineralFilter input for autocomplete:
        this.filteredMinerals = this.mineralFilter.valueChanges.pipe(
            startWith( '' ),
            map( value => typeof value === 'string' ? value : value.dutchName ),
            map( dutchName => dutchName ? this._filter( dutchName ) : this.minerals.slice() )
        );
    }

    updateForm( specimen: ISpecimen ): void {
        this.editForm.patchValue( {
            id: specimen.id,
            code: specimen.code,
            acquiredDate: specimen.acquiredDate,
            acquiredAt: specimen.acquiredAt,
            acquiredPrice: specimen.acquiredPrice,
            acquiredBy: specimen.acquiredBy,
            acquiredFrom: specimen.acquiredFrom,
            remark: specimen.remark,
            fluorescent: specimen.fluorescent,
            statusId: specimen.statusId,
            storageLocationId: specimen.storageLocationId,
            locationId: specimen.locationId,
            minerals: specimen.minerals
        } );
    }

    previousState(): void {
        window.history.back();
    }

    save(): void {
        this.isSaving = true;
        const specimen = this.createFromForm();
        if ( specimen.id !== undefined ) {
            this.subscribeToSaveResponse( this.specimenService.update( specimen ) );
        } else {
            this.subscribeToSaveResponse( this.specimenService.create( specimen ) );
        }
    }

    private createFromForm(): ISpecimen {
        return {
            ...new Specimen(),
            id: this.editForm.get( ['id'] )!.value,
            code: this.editForm.get( ['code'] )!.value,
            acquiredDate: this.editForm.get( ['acquiredDate'] )!.value,
            acquiredAt: this.editForm.get( ['acquiredAt'] )!.value,
            acquiredPrice: this.editForm.get( ['acquiredPrice'] )!.value,
            acquiredBy: this.editForm.get( ['acquiredBy'] )!.value,
            acquiredFrom: this.editForm.get( ['acquiredFrom'] )!.value,
            remark: this.editForm.get( ['remark'] )!.value,
            fluorescent: this.editForm.get( ['fluorescent'] )!.value,
            statusId: this.editForm.get( ['statusId'] )!.value,
            storageLocationId: this.editForm.get(['storageLocationId'])!.value,
            locationId: this.editForm.get( ['locationId'] )!.value,
            minerals: this.editForm.get( ['minerals'] )!.value
        };
    }

    protected subscribeToSaveResponse( result: Observable<HttpResponse<ISpecimen>> ): void {
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

    // ---------------------------------------------------------------------------------------------------------------
    // Functions specific to the mineral list:
    // ---------------------------------------------------------------------------------------------------------------

    addSelectedMineral(): void {
        console.error(this.mineralFilter.value);
        // First check if mineralFilter contains a correct IMineral value:
        if (!this.mineralFilter.value || typeof this.mineralFilter.value === 'string') {
            return;
        }
        
        // Is it the first mineral, or do we add to the list?
        const selection = this.editForm.get( ['minerals'] )!.value;
        if ( selection ) {
            for ( let i = 0; i < selection.length; i++ ) {
                if ( this.mineralFilter.value.id === selection[i].id ) {
                    // Mineral is already in list. Let's not add it again.
                    return;
                }
            }
            selection.push( this.mineralFilter.value );
            this.editForm.patchValue( {
                minerals: selection
            } );
        } else {
            this.editForm.patchValue( {
                minerals: [this.mineralFilter.value]
            } );
        }
       
        // Now cleanup the mineralFilter:
        this.mineralFilter.setValue('');
        this.hasValidMineral = false;
    }

    removeMineral( mineral: IMineral ): void {
        const selection = this.editForm.get( ['minerals'] )!.value;
        if ( selection ) {
            for ( let i = 0; i < selection.length; i++ ) {
                if ( mineral.id === selection[i].id ) {
                    mineral.id === selection.splice( i, 1 );
                    this.editForm.patchValue( {
                        minerals: selection
                    } );
                }
            }
        }
    }

    asText( mineral: IMineral ): string {
        return mineral && mineral.dutchName ? mineral.dutchName : '';
    }

    private _filter( value: string ): IMineral[] {
        this.hasValidMineral = this.mineralFilter.value && this.mineralFilter.value.id; // Ideally this also checks doubles....
        const filterValue = value.toLowerCase();
        return this.minerals.filter( option => option.dutchName!.toLowerCase().includes( filterValue ) );
    }
}
