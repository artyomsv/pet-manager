export interface IFeedRecord {
  id?: any;
  index?: number;
  date?: Date;
  person?: string;
  food?: string;
  foodGiven?: number;
  foodEaten?: number;
  version?: number;
  createdBy?: string;
  createdDate?: Date | null;
  lastModifiedBy?: string;
  lastModifiedDate?: Date | null;
}

export const defaultValue: Readonly<IFeedRecord> = {
  id: null,
  index: 0,
  date: null,
  person: '',
  food: '',
  foodGiven: 0,
  foodEaten: null,
  version: null,
  createdBy: null,
  createdDate: null,
  lastModifiedBy: null,
  lastModifiedDate: null,
};
