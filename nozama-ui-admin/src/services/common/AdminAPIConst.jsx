export const APIGW_URL = process.env.APIGW_URL?process.env.APIGW_URL:"https://nozama.in:8090/";
export const PRODUCT_SVC_URL = APIGW_URL + "product/";
export const ADD_PRODUCT_SVC_URL = PRODUCT_SVC_URL + "add";
export const UPLOAD_PRODUCT_IMG_SVC_URL = PRODUCT_SVC_URL + "upload";


export const CATEGORY_SVC_URL = APIGW_URL + "category/";
export const ALL_CATEGORY_SVC_URL = CATEGORY_SVC_URL + "all";