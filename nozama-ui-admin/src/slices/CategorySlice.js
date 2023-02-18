
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import CategoryService from "../services/CategoryService";

const initialState = {
    category:[]
};

export const fetchAllCategory = createAsyncThunk(
    "category/fetchAllCategory",
    async () => { 
        return await CategoryService.getAllCategory();
    }
);

const CategorySlice = createSlice({
    name: "category",
    initialState,
    reducers: {
        getAllCategory: (state, { payload }) => { 
            state.category = payload;
        }
    },
    extraReducers: {
        [fetchAllCategory.fulfilled]: (state, { payload }) => { 
            return { ...state, category: payload };
        }
    }
});

export const { category } = CategorySlice.actions;
export const getAllCategory = (state) => state.category.category;
export default CategorySlice.reducer;