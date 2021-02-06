export interface IFileImport {
  dataContentType?: string;
  data?: any;
  importLocations?: boolean;
  importSpecimen?: boolean;
}

export class FileImport implements IFileImport {
  constructor(
    public dataContentType?: string,
    public data?: any,
    public importLocations?: boolean,
    public importSpecimen?: boolean
  ) {}
}

export interface IImportMessage {
    message?: string;
}
