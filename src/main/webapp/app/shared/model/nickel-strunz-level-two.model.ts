export interface INickelStrunzLevelTwo {
  id?: number;
  code?: string;
  name?: string;
  parentName?: string;
  parentId?: number;
}

export class NickelStrunzLevelTwo implements INickelStrunzLevelTwo {
  constructor(public id?: number, public code?: string, public name?: string, public parentName?: string, public parentId?: number) {}
}
