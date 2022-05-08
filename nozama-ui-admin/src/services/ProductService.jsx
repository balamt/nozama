import axios from "axios";
import {
  ADD_PRODUCT_SVC_URL,
  UPLOAD_PRODUCT_IMG_SVC_URL,
} from "./common/AdminAPIConst";
import ProductRequest from "./common/ProductRequest";

class ProductService {
  addProduct(product) {
    console.log(JSON.stringify(ProductRequest.getProduct()));
    return axios.post(ADD_PRODUCT_SVC_URL, product).then((response) => {
      console.log(response.data);
      return response.data;
    });
  }

  uploadProductImage(product, imageFile, onUploadProgress) {
    console.log(product.productId);
    console.log(imageFile);
    let formdata = new FormData();
    formdata.append("productid", product.productId);
    formdata.append("productimg", imageFile);
    return axios.post(
      UPLOAD_PRODUCT_IMG_SVC_URL,
      formdata,
      {
        headers: { "Content-Type": "multipart/form-data" }, onUploadProgress
      });
  }
}

export default new ProductService();
