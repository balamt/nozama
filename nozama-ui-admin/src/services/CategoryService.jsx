import axios from "axios";

import {
    ALL_CATEGORY_SVC_URL,
  } from "./common/AdminAPIConst";
  
  class CategoryRequest {
    getAllCategory() {
      return axios.get(ALL_CATEGORY_SVC_URL).then((response) => {
        return response.data;
      });
    }
   
  }
  
  export default new CategoryRequest();
  