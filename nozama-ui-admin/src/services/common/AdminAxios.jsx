import axios from "axios";
import UserService from "../UserService.jsx";
import { APIGW_URL } from "./AdminAPIConst.jsx";

const AdminAxios = axios.create({
  baseURL: APIGW_URL,
});

AdminAxios.interceptors.request.use((config) => {
  if (!config.url) {
    return config;
  }

  const currentUrl = new URL(config.url, config.baseURL);
  // parse pathName to implement variables
  Object.entries(config.urlParams || {}).forEach(([k, v]) => {
    console.log(currentUrl.pathname);
    currentUrl.pathname = currentUrl.pathname.replace(
      `:${k}`,
      encodeURIComponent(v)
    );
    console.log(currentUrl.pathname);
  });

  const authPart =
    currentUrl.username && currentUrl.password
      ? `${currentUrl.username}:${currentUrl.password}`
      : "";
  return {
    ...config,
    baseURL: `${currentUrl.protocol}//${authPart}${currentUrl.host}`,
    url: currentUrl.pathname,
  };
});

export default AdminAxios;
