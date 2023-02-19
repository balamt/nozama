import React from "react";
import { Button } from "react-bootstrap";
import { Card } from "react-bootstrap";
import { Image } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartPlus } from "@fortawesome/free-solid-svg-icons";
import "./ProductItem.css";
import AppConst from "../Util/app.const";

export const ProductItem = ({ product }) => {
  return (
    <Card className="bg-warning card-item-width">
      <Link
        style={{
          textDecoration: "none",
          color: "white",
        }}
        to={"/product/" + product.productId}
      >
        <Card.Body className="card-body">
          <div className="card-image-price-container">
            <Image
              className="card-product-image"
              height={120}
              width={120}
              src={AppConst().PRODUCT_IMAGE_SVC_URL + product.productId}
            />
            <p className="text-primary card-product-price">
              ₹ {product.pricePerItem}
            </p>
          </div>

          <div>
            <h4
              className="text-primary card-product-name"
              style={{
                textOverflow: "hidden",
                fontWeight: "bolder",
              }}
            >
              {product.productName}
            </h4>
            <div
              style={{
                display: "flex",
                alignItems: "stretch",
                justifyContent: "space-between",
              }}
            >
              <p className="card-product-description">
                {product.productDescription}
              </p>
            </div>
            <div>
              <Button>
                Buy Now <FontAwesomeIcon icon={faCartPlus} />
              </Button>
            </div>
          </div>
        </Card.Body>
      </Link>
    </Card>
  );
};
