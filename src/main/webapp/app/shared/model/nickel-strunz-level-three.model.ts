export interface INickelStrunzLevelThree {
  id?: number;
  code?: string;
  name?: string;
  shortName?: string;
  parentName?: string;
  parentId?: number;
}

export class NickelStrunzLevelThree implements INickelStrunzLevelThree {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public shortName?: string,
    public parentName?: string,
    public parentId?: number
  ) {}
}
