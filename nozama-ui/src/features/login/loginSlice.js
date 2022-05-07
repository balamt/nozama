import { createSlice } from "@reduxjs/toolkit";
import dayjs from "dayjs";

import AuthService from "../../components/Auth/AuthService";

const initialState = {
  login: {},
  token: {},
  tokenValidTo: {},
  isTokenExpired: true,
  userLoggedIn: false,
  tokenExpiryInMinutes: 0,
};

//Extract the Login payload and get the validaty datetime and check with current date and time.
function checkValidity(payload) {
  if (payload === undefined) {
    return true;
  }
  const tokenValidTo = payload.validTo;
  const tokenDT = dayjs(tokenValidTo);
  const now = dayjs();
  return now > tokenDT;
}

function getTokenExpiry(payload) {
  if (payload === undefined) {
    return 0;
  }
  const tokenValidTo = payload.validTo;
  const tokenDT = dayjs(tokenValidTo);
  const now = dayjs();
  return tokenDT.diff(now, "minute");
}

const loginSlice = createSlice({
  name: "login",
  initialState,
  reducers: {
    login: (state, { payload }) => {
      state.login = payload;
      state.token = payload?.token;
      state.tokenValidTo = payload?.validTo;
      state.isTokenExpired = checkValidity(payload);
      state.tokenExpiryInMinutes = getTokenExpiry(payload);
      if (payload?.token) {
        state.userLoggedIn = true;
      }
    },
    logout: (state) => {
      state.login = {};
      state.token = {};
      state.tokenValidTo = {};
      state.isTokenExpired = true;
      state.tokenExpiryInMinutes = 0;
      state.userLoggedIn = false;
      AuthService.logout();
    },
  },
});

export const { login } = loginSlice.actions;
export const { logout } = loginSlice.actions;
export const getLogin = (state) => state.login;
export const getToken = (state) => state.login.token;
export const getTokenValidTo = (state) => state.login.tokenValidTo;
export const isTokenExpired = (state) => state.login.isTokenExpired;
export const getTokenExpiryInMinutes = (state) =>
  state.login.tokenExpiryInMinutes;
export const isUserLoggedIn = (state) => state.login.userLoggedIn;
export default loginSlice.reducer;
