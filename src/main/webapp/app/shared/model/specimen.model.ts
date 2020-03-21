import { Moment } from 'moment';
import { IMineral } from 'app/shared/model/mineral.model';
import { ISeries } from 'app/shared/model/series.model';

export interface ISpecimen {
  id?: number;
  code?: string;
  acquiredDate?: Moment;
  acquiredAt?: string;
  acquiredPrice?: number;
  acquiredBy?: string;
  acquiredFrom?: string;
  remark?: string;
  fluorescent?: boolean;
  statusName?: string;
  statusId?: number;
  locationShortName?: string;
  locationId?: number;
  minerals?: IMineral[];
  series?: ISeries[];
}

export class Specimen implements ISpecimen {
  constructor(
    public id?: number,
    public code?: string,
    public acquiredDate?: Moment,
    public acquiredAt?: string,
    public acquiredPrice?: number,
    public acquiredBy?: string,
    public acquiredFrom?: string,
    public remark?: string,
    public fluorescent?: boolean,
    public statusName?: string,
    public statusId?: number,
    public locationShortName?: string,
    public locationId?: number,
    public minerals?: IMineral[],
    public series?: ISeries[]
  ) {
    this.fluorescent = this.fluorescent || false;
  }
}
