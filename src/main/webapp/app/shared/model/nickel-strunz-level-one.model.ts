export interface INickelStrunzLevelOne {
  id?: number;
  code?: string;
  name?: string;
}

export class NickelStrunzLevelOne implements INickelStrunzLevelOne {
  constructor(public id?: number, public code?: string, public name?: string) {}
}
