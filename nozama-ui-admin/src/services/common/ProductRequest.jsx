export const PRODICT_NAME = "productName";

let product = {
  productCode: "",
  productName: "",
  productDescription: "",
  category: "",
  stockQuantity: 0,
  pricePerItem: 0,
  warehouse: 0,
  productImg: "",
  tags: [],
  seller: 0,
  rating: 0,
};
class ProductRequest {
  constructor(product) {
    this.product = product;
  }

  getProduct() {
    return this.product;
  }
}

export default new ProductRequest(product);
