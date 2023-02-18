import React, { useState, useRef, useEffect } from "react";
import {
  Button,
  Alert,
  Card,
  Row,
  Col,
  Overlay,
  Image,
  Badge,
  Container,
} from "react-bootstrap";

import thumb_preview from "../../resources/images/image_thumb.svg";
import { ADDRESS_CITY, ADDRESS_COUNTRY, ADDRESS_LINE1, ADDRESS_LINE2, ADDRESS_PINCODE, ADDRESS_STATE, ADDRESS_STREET, ADDRESS_TYPE } from "../../services/common/AddressRequest";
import UserRequest, {
  USER_EMAIL,
  USER_FULLNAME,
  USER_MOBILE,
  USER_GENDER,
  USER_TYPE,
  USER_ADDRESS,
} from "../../services/common/UserRequest";

const UserPreviewCard = ({ user, uimage }) => {
  const [state] = useState(user.user);
  const [showAddCartToolTip, setShowAddCartToolTip] = useState(false);
  const target = useRef(null);

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
                  src={uimage ? uimage : thumb_preview}
                />
                <Container
                  fluid
                  className="mx-2"
                  style={{
                    position: "absolute",
                    fontSize: "1.2em",
                  }}
                >
                  <Badge bg="warning" text="secondary">
                    {state[USER_TYPE]}
                  </Badge>
                </Container>
              </div>

              <Card.Body ref={target} className="d-flex flex-column">
                <Card.Title className="text-primary">
                  <h3>
                    {state[USER_FULLNAME] ? state[USER_FULLNAME] : "Full Name"}
                  </h3>
                </Card.Title>

                <Card.Subtitle>
                  <Row>
                    <Col>
                      <span className="text-warning">
                        {state[USER_MOBILE] ? state[USER_MOBILE] : "9876543210"}
                      </span>
                    </Col>
                    <Col>
                      {state[USER_EMAIL]
                        ? state[USER_EMAIL]
                        : "dummy@email.com"}
                    </Col>
                  </Row>
                </Card.Subtitle>
                <Card.Text>
                  <Row>
                    <Col>
                      {state[USER_GENDER]
                        ? state[USER_GENDER]
                        : "Not Disclosed"}
                      <hr />
                      Address : <br />{`${state[USER_ADDRESS][ADDRESS_LINE1]} , ${state[USER_ADDRESS][ADDRESS_LINE2]} , ${state[USER_ADDRESS][ADDRESS_STREET]}, ${state[USER_ADDRESS][ADDRESS_CITY]}, ${state[USER_ADDRESS][ADDRESS_STATE]}, ${state[USER_ADDRESS][ADDRESS_COUNTRY]} - ${state[USER_ADDRESS][ADDRESS_PINCODE]}`}
                      <hr />
                      Address Type: <Badge bg="secondary">{state[USER_ADDRESS][ADDRESS_TYPE]}</Badge>
                    </Col>
                  </Row>
                </Card.Text>
              </Card.Body>
            </Card.Link>
          </Card>
        </Col>
      </Row>
    </>
  );
};

export default UserPreviewCard;
