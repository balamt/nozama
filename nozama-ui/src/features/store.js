import { configureStore } from "@reduxjs/toolkit";

import loginReducer from "./login/loginSlice.js";

export const store = configureStore({
  reducer: {
    login: loginReducer,
  },
});
