import React, { useEffect, useReducer, useRef, useState } from "react";
import { run as runHolder } from "holderjs/holder";
import { Button, Alert, Card, Row, Col, Overlay, Image } from "react-bootstrap";

import { FaHeart, FaRegHeart } from "react-icons/fa";
import QuantityComponent from "./QuantityComponent";
import thumb_preview from "../../resources/images/image_thumb.svg";

function ProductPreviewCard({
  name,
  description,
  pimage,
  tags,
  rate,
  quantity,
}) {
  useEffect(() => {
    runHolder(".prod-img");
  });

  const [favIconStyle, setFavIconStyle] = useState({
    position: "relative",
    marginLeft: "-52px",
    marginTop: "12px",
    padding: "5px",
  });
  const [mouseOverFav, setMouseOverFav] = useState(false);
  const [favClicked, setFavClicked] = useState(false);
  const [showAddCartToolTip, setShowAddCartToolTip] = useState(false);
  const target = useRef(null);

  const mouseOnFavourite = () => {
    if (!favClicked) {
      setMouseOverFav(!mouseOverFav);

      //Mouse Hover change the icon color
      if (mouseOverFav) {
        setFavIconStyle({ ...favIconStyle, color: "red" });
      } else {
        setFavIconStyle({ ...favIconStyle, color: "" });
      }
    }
  };

  const favAdded = () => {
    setFavClicked(!favClicked);
    setMouseOverFav(false);
    setFavIconStyle({ ...favIconStyle, color: "red" });
  };

  const quantityCallBack = (value) => {
    console.log(value);
  };

  return (
    <>
      <Row className="d-flex flex-row align-items-center">
        <Col className="mb-2 mt-2">
          <Card>
            <Overlay
              target={target.current}
              show={showAddCartToolTip}
              id="button-tooltip"
            >
              <Alert
                style={{
                  fontSize: "1.3em",
                }}
                className={`alert alert-success ${
                  showAddCartToolTip ? "alert-shown" : "alert-hidden"
                }`}
                onTransitionEnd={() => setShowAddCartToolTip(false)}
              >
                Added to Cart
              </Alert>
            </Overlay>
            <Card.Link className="text-decoration-none" href="#">
              <div className="d-flex">
                <Card.Img
                  as={Image}
                  fluid
                  // className="prod-img"
                  src={pimage ? pimage : thumb_preview}
                />
                {/* <img
                  src={image}
                  alt=""
                  className="card-img card-image-bottom prod-img"
                /> */}
                {mouseOverFav ? (
                  <FaRegHeart
                    size={42}
                    style={favIconStyle}
                    onMouseEnter={mouseOnFavourite}
                  />
                ) : (
                  <FaHeart
                    size={42}
                    style={favIconStyle}
                    onMouseLeave={mouseOnFavourite}
                    onClick={favAdded}
                  />
                )}
              </div>

              <Card.Body ref={target} className="d-flex flex-column">
                <Card.Title className="text-primary">
                  {name ? name : "Product Name"}
                </Card.Title>

                <Card.Subtitle>
                  <Row>
                    <Col>
                      <h5 className="text-secondary">
                        {rate ? rate : "1200.00 "} ₹
                      </h5>
                    </Col>
                    <Col>
                      <QuantityComponent
                        max={quantity}
                        getQuantity={quantityCallBack}
                      />
                    </Col>
                  </Row>
                </Card.Subtitle>
                <Card.Text>
                  {description
                    ? description
                    : "Some quick example text to build on the card title and make up the bulk of the card's content."}
                </Card.Text>
                <Button
                  disabled={quantity <= 0 ? true : false}
                  onClick={() => {
                    if (quantity >= 1) {
                      setShowAddCartToolTip(!showAddCartToolTip);
                    }
                  }}
                  variant={quantity <= 0 ? "light" : "success"}
                >
                  Add Cart
                </Button>
              </Card.Body>
            </Card.Link>
          </Card>
          <sub>{tags}</sub>
        </Col>
      </Row>
    </>
  );
}

export default ProductPreviewCard;
