import { ISpecimen } from 'app/shared/model/specimen.model';

export interface IMineral {
  id?: number;
  dutchName?: string;
  formula?: string;
  hardnessMin?: number;
  hardnessMax?: number;
  crystalSystem?: string;
  nickelStruntzLevelFour?: string;
  mindatUrl?: string;
  nickelStrunzLevelThreeName?: string;
  nickelStrunzLevelThreeId?: number;
  specimens?: ISpecimen[];
}

export class Mineral implements IMineral {
  constructor(
    public id?: number,
    public dutchName?: string,
    public formula?: string,
    public hardnessMin?: number,
    public hardnessMax?: number,
    public crystalSystem?: string,
    public nickelStruntzLevelFour?: string,
    public mindatUrl?: string,
    public nickelStrunzLevelThreeName?: string,
    public nickelStrunzLevelThreeId?: number,
    public specimens?: ISpecimen[]
  ) {}
}
