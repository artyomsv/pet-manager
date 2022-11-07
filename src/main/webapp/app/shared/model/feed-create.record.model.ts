export interface IFeedCreateRecord {
  date?: Date;
  person?: string;
  food?: string;
  foodGiven?: number;
}

export const defaultValue: Readonly<IFeedCreateRecord> = {
  date: null,
  person: null,
  food: null,
  foodGiven: null,
};
