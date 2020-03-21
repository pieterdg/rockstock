import { ISpecimen } from 'app/shared/model/specimen.model';

export interface ISeries {
  id?: number;
  name?: string;
  description?: string;
  specimen?: ISpecimen[];
}

export class Series implements ISeries {
  constructor(public id?: number, public name?: string, public description?: string, public specimen?: ISpecimen[]) {}
}
