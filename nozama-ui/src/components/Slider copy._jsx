import React from "react";
import "jquery";
import { Button } from "react-bootstrap";
import { Image } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartPlus } from "@fortawesome/free-solid-svg-icons";
import logo from "./img/logo.png";
import { Card } from "react-bootstrap";
import { Carousel } from "react-bootstrap";
import { CardGroup } from "react-bootstrap";
import { Placeholder } from "react-bootstrap";
import { Row } from "react-bootstrap";
import { Col } from "react-bootstrap";
import { ProductItem } from "./Products/ProductItem";

function Slider(props) {
  const products = props.products ? props.products : [];
  const renderProduct = products.map((product, index) => {
    return <ProductItem product={product} key={index} />;
  });
  return (
    <div className="container bg-info">
      <Row>
        <Col>
          <Carousel controls={false} indicators={false} interval={20}>
            <Carousel.Item>
              <CardGroup>{renderProduct}</CardGroup>
            </Carousel.Item>
          </Carousel>
        </Col>
      </Row>
    </div>
  );
}

export default Slider;
