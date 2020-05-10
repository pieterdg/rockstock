export interface IStorageLocation {
  id?: number;
  name?: string;
}

export class StorageLocation implements IStorageLocation {
  constructor(public id?: number, public name?: string) {}
}
