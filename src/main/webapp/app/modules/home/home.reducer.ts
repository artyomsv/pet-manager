import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import axios from "axios";
import {defaultValue, IFeedCreateRecord} from "app/shared/model/feed-create.record.model";
import {serializeAxiosError} from "app/shared/reducers/reducer.utils";
import {IFeedRecord} from "app/shared/model/feed.record.model";

const initialState = {
  loading: false,
  creating: false,
  errorMessage: null,
  totalItems: 0,
  records: [] as IFeedRecord[],
  createRecord: defaultValue,
  updateRecord: defaultValue
};

export type HomeState = Readonly<typeof initialState>;

const apiUrl = 'api/feed-records';
// Actions

export const getCatRecords = createAsyncThunk(
  'catRecordsManagement/fetch_feed_records',
  async (date: string) => {
    const requestUrl = `${apiUrl}${date ? `?date=${date}` : ''}`;
    return axios.get<IFeedRecord[]>(requestUrl);
  },
  {serializeError: serializeAxiosError}
);

export const createRecord = createAsyncThunk(
  'catRecordsManagement/create_feed_record',
  async (record: IFeedCreateRecord, thunkAPI) => {
    const result = await axios.post<IFeedCreateRecord>(apiUrl, record);
    thunkAPI.dispatch(getCatRecords("2022-11-07"));
    return result;
  },
  {serializeError: serializeAxiosError}
);


export const HomeSlice = createSlice({
  name: 'home',
  initialState: initialState as HomeState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(getCatRecords.fulfilled, (state, action) => {
        state.loading = false;
        state.records = action.payload.data;
      })
    ;
  },
});

// Reducer
export default HomeSlice.reducer;
