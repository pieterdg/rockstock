import { ISpecimen } from 'app/shared/model/specimen.model';
import { ILocation } from 'app/shared/model/location.model';
import { IMineral } from 'app/shared/model/mineral.model';

export interface ISearchResult {
    specimen?: ISpecimen[];
    locations?: ILocation[];
    minerals?: IMineral[];
}

export class SearchResult implements ISearchResult {
    constructor( public ispecimend?: ISpecimen[], public locations?: ILocation[], public minerals?: IMineral[] ) { }
}
