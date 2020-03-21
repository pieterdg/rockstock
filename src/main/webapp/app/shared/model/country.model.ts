export interface ICountry {
  id?: number;
  countryName?: string;
  shortName?: string;
}

export class Country implements ICountry {
  constructor(public id?: number, public countryName?: string, public shortName?: string) {}
}
