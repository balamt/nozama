export const APIGW_URL = process.env.APIGW_URL?process.env.APIGW_URL:"https://nozama.in:8090/";
export const PRODUCT_SVC_URL = APIGW_URL + "product/";
export const ADD_PRODUCT_SVC_URL = PRODUCT_SVC_URL + "add";
export const UPLOAD_PRODUCT_IMG_SVC_URL = PRODUCT_SVC_URL + "upload";


export const CATEGORY_SVC_URL = APIGW_URL + "category/";
export const ALL_CATEGORY_SVC_URL = CATEGORY_SVC_URL + "all";

export const USER_SVC_URL = APIGW_URL + "user/";
export const USER_LOGIN_SVC_URL = USER_SVC_URL + "login";
export const USER_BY_ID_SVC_URL = USER_SVC_URL + "view/:id";
export const USER_BY_USERNAME_SVC_URL = USER_SVC_URL + "email/:email";

export const AUTH_SVC_URL = APIGW_URL + "auth/";
export const AUTH_TOKEN_SVC_URL = AUTH_SVC_URL + "token";
export const AUTH_RENEW_TOKEN_SVC_URL = AUTH_TOKEN_SVC_URL + "/renew";