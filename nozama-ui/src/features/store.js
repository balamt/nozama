import { configureStore } from "@reduxjs/toolkit";

import loginReducer from "./login/loginSlice.js";
import productReducer from "./product/productSlice.js";

export const store = configureStore({
  reducer: {
    login: loginReducer,
    product: productReducer,
  },
});
