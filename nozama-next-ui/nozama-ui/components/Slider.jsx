import "jquery";
import logo from "./img/logo.png";
import Image from "next/image";
import { FaCartPlus } from "react-icons/fa";
import {
  Button,
  Carousel,
  CardGroup,
  Card,
  Placeholder,
  Row,
  Col,
} from "react-bootstrap";
import ProductItem from "./ProductItem";

export default function Slider(props) {
  return (
    <div className="flex w-fit bg-white rounded-sm">
      <div className="flex lg:inline-flex">
        <ProductItem
          productId={123}
          image={logo}
          title="Computer 500X"
          desc="Computer with 500GB HDD, 16GB RAM, 14 inch. Sold By XYZ, Computer with 500GB HDD, 16GB RAM, 14 inch. Sold By XYZ, Computer with 500GB HDD, 16GB RAM, 14 inch. Sold By XYZ"
          skuCode=""
          price={29999.0}
          discount={20}
        />
        <ProductItem
          productId={123}
          image={logo}
          title="Mouse 500X"
          desc="High Speed Optical Mouse for Work From home."
          skuCode=""
          price={999.0}
          discount={4}
        />
      </div>
    </div>
  );
}
