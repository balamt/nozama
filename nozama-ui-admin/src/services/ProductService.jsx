import axios from "axios";
import {
  ADD_PRODUCT_SVC_URL,
  UPLOAD_PRODUCT_IMG_SVC_URL,
} from "./common/AdminAPIConst";
import ProductRequest from "./common/ProductRequest";

class ProductService {
  
  /*
   * Add Product 
   */
  addProduct(product) {
    console.log(JSON.stringify(ProductRequest.getProduct()));
    return axios.post(ADD_PRODUCT_SVC_URL, product).then((response) => {
      console.log(response.data);
      return response.data;
    });
  }

  /*
   * Upload Product Image 
   */
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

  /*
   * Search Product by ID or Name
   */
  searchProductByIdOrName(product) { 
    //API Not implemented
    // Need to create endpoint which can accept query string and based on the string it need to search the product table
  }

  /*
   * Edit Product 
   */
  editProduct(product) { 
    // Edit/Update Product service is not Implemented.
  }

}

export default new ProductService();
