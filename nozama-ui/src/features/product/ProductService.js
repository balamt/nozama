import axios from "axios";
import AppConst from "../../components/Util/app.const";

class ProductService {
  fetchAllProducts() {
    return axios.get(AppConst().PRODUCT_SVC_URL + "all").then((response) => {
      return response.data;
    });
  }

  fetchProductById(productId) {
    return axios
      .get(AppConst().PRODUCT_SVC_URL + productId)
      .then((response) => {
        return response.data;
      });
  }

  fetchProductImageById(productId) {
    return AppConst().PRODUCT_IMAGE_SVC_URL + productId;
  }

  fetchProductByCategory(categoryId) {
    return axios
      .get(AppConst().PRODUCT_SVC_URL + "by/category/" + categoryId)
      .then((response) => {
        return response.data.content;
      });
  }

  fetchAllAvailableCategories() {
    return axios.get(AppConst().CATEGORY_SVC_URL + "all").then((response) => {
      return response.data;
    });
  }
}

export default new ProductService();
