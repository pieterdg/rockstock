export interface ILocation {
  id?: number;
  shortName?: string;
  stateProvince?: string;
  city?: string;
  mine?: string;
  xPosition?: number;
  yPosition?: number;
  countryCountryName?: string;
  countryId?: number;
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public shortName?: string,
    public stateProvince?: string,
    public city?: string,
    public mine?: string,
    public xPosition?: number,
    public yPosition?: number,
    public countryCountryName?: string,
    public countryId?: number
  ) {}
}
