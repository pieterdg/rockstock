export interface ISpecimenStatus {
  id?: number;
  name?: string;
}

export class SpecimenStatus implements ISpecimenStatus {
  constructor(public id?: number, public name?: string) {}
}
