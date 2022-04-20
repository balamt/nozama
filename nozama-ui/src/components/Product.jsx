import Slider from "./Slider";
import { Stack } from "react-bootstrap";
import { useSelector } from "react-redux";
import { getAllProducts } from "../features/product/productSlice";

const Product = (props) => {
  let products = useSelector(getAllProducts);

  if (Object.keys(products) <= 0) {
    products = [];
  }

  return <Slider products={products} />;
};

export default Product;
