import { React, useState } from "react";
import {
  Button,
  Carousel,
  CardGroup,
  Card,
  Placeholder,
  Row,
  Col,
} from "react-bootstrap";
import { FaCartPlus, FaRegHeart, FaHeart } from "react-icons/fa";
import Image from "next/image";
import logo from "./img/logo.png";

function ProductItem(props) {
  const [productId] = useState(props.productId);
  const [image] = useState(props.image);
  const [title] = useState(props.title);
  const [price] = useState(props.price);
  const [discount] = useState(props.discount);
  const [desc] = useState(props.desc);

  return (
    <Card className="inline-flex items-center mx-1 my-3 cursor-pointer bg-slate-200 max-w-xs">
      <Card.Body className="flex flex-col">
        <div className="block">
          <p className="float-left text-2xl font-extrabold">{title}</p>
          <FaRegHeart
            svg
            size={32}
            className="float-right fill-gray-500 items-end mr-0 pr-0 hover:fill-pink-600"
          />
        </div>
        <div className="flex flex-wrap">
          <Image
            className="object-scale-down"
            src={image}
            height={320}
            alt="Prod1"
          />
          <p className="text-lg font-semibold flex-nowrap">{"₹ " + price}</p>
          &nbsp;
          <p className="text-sm text-secondary font-semibold flex-nowrap">
            {discount + " % OFF"}
          </p>
        </div>
        <div className="flex flex-wrap">
          <p className="text-xs text-ellipsis h-9 flex-wrap overflow-hidden">
            {desc}
          </p>
          <Button className="inline-flex items-end">
            Buy Now <FaCartPlus size={22} />
          </Button>
        </div>
      </Card.Body>
    </Card>
  );
}

export default ProductItem;
