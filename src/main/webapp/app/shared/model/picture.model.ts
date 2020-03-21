export interface IPicture {
  id?: number;
  dataContentType?: string;
  data?: any;
  description?: string;
  specimenCode?: string;
  specimenId?: number;
  locationShortName?: string;
  locationId?: number;
}

export class Picture implements IPicture {
  constructor(
    public id?: number,
    public dataContentType?: string,
    public data?: any,
    public description?: string,
    public specimenCode?: string,
    public specimenId?: number,
    public locationShortName?: string,
    public locationId?: number
  ) {}
}
