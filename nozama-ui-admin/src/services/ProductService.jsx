import axios from "axios";
import ProductRequest from "./common/ProductRequest";

class ProductService {
  addProduct(products) {
    console.log(ProductRequest.getProduct());
  }
}

export default new ProductService();
