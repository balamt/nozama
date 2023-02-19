import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

import ProductService from "./ProductService";

export const fetchAsyncProducts = createAsyncThunk(
  "product/fetchAsyncProducts",
  async () => {
    return await ProductService.fetchAllProducts();
  }
);

export const fetchAsyncProductByCategory = createAsyncThunk(
  "product/fetchAsyncProductByCategory",
  async (categoryId) => {
    let data = await ProductService.fetchProductByCategory(categoryId);
    console.log(data);
    return data;
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

export const fetchAsyncAllAvailableCategories = createAsyncThunk(
  "product/fetchAsyncAllAvailableCategories",
  async () => {
    return await ProductService.fetchAllAvailableCategories();
  }
);

const initialState = {
  products: {},
  categories: {},
  selectedProduct: {},
  selectedProductImage: {},
  productByCategory: {},
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
    [fetchAsyncProductByCategory.fulfilled]: (state, { payload }) => {
      console.log("Product By Category Fulfilled");
      return { ...state, productByCategory: payload };
    },
    [fetchAsyncAllAvailableCategories.fulfilled]: (state, { payload }) => {
      console.log("All Category Fulfilled");
      return { ...state, categories: payload };
    },
  },
});

export const { product } = productSlice.actions;
export const { category } = productSlice.actions;
export const getAllProducts = (state) => state.product.products;
export const getAllAvailableCategories = (state) => state.product.categories;
export const getSelectedProduct = (state) => state.product.selectedProduct;
export const getSelectedProductImage = (state) =>
  state.product.selectedProductImage;
export const getProductByCategory = (state) => state.product.productByCategory;
export default productSlice.reducer;
