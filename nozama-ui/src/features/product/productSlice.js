import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

import ProductService from "./ProductService";

export const fetchAsyncProducts = createAsyncThunk(
  "product/fetchAsyncProducts",
  async () => {
    return await ProductService.fetchAllProducts();
  }
);

export const fetchAsyncProductById = createAsyncThunk(
  "product/fetchAsyncProductById",
  async (productId) => {
    return await ProductService.fetchProductById(productId);
  }
);

export const fetchAsyncProductImageById = createAsyncThunk(
  "product/fetchAsyncProductImageById",
  async (productId) => {
    return await ProductService.fetchProductImageById(productId);
  }
);

const initialState = {
  products: {},
  categories: {},
  selectedProduct: {},
  selectedProductImage: {},
};

const productSlice = createSlice({
  name: "product",
  initialState,
  reducers: {
    addProduct: (state, { payload }) => {
      state.products = payload;
    },
    addCategory: (state, { payload }) => {},
  },
  extraReducers: {
    [fetchAsyncProducts.pending]: () => {
      console.log("Product Fetch");
    },
    [fetchAsyncProducts.fulfilled]: (state, { payload }) => {
      console.log("Product Fulfilled");
      return { ...state, products: payload };
    },
    [fetchAsyncProducts.rejected]: () => {
      console.log("Product Rejected");
    },
    [fetchAsyncProductById.fulfilled]: (state, { payload }) => {
      console.log("Product By ID Fulfilled");
      return { ...state, selectedProduct: payload };
    },
    [fetchAsyncProductImageById.fulfilled]: (state, { payload }) => {
      console.log("Product Image By ID Fulfilled");
      return { ...state, selectedProductImage: payload };
    },
  },
});

export const { product } = productSlice.actions;
export const { category } = productSlice.actions;
export const getAllProducts = (state) => state.product.products;
export const getAllCategories = (state) => state.product.categories;
export const getSelectedProduct = (state) => state.product.selectedProduct;
export const getSelectedProductImage = (state) =>
  state.product.selectedProductImage;
export default productSlice.reducer;
