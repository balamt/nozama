import React from "react";
import "jquery";

import logo from "./img/logo.png";
import { Carousel } from "react-bootstrap";
import { CardGroup } from "react-bootstrap";
import { Card } from "react-bootstrap";
import { Image } from "react-bootstrap";
import { Button } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartPlus } from "@fortawesome/free-solid-svg-icons";
import { Placeholder } from "react-bootstrap";
import { Row } from "react-bootstrap";
import { Col } from "react-bootstrap";

export class Slider extends React.Component {
  //   constructor(props) {
  //     super(props);
  //   }

  render() {
    return (
      <div className="container bg-info">
        <Row>
          <Col>
            <Carousel indicators={false} interval={null}>
              <Carousel.Item>
                <CardGroup>
                  <Card className="bg-warning card-item-width">
                    <Card.Img variant="top" src={logo} />
                    <Card.Body>
                      <Placeholder as={Card.Title} animation="glow">
                        <Placeholder xs={6} />
                      </Placeholder>
                      <Placeholder.Button xs={6} animation="glow" />
                    </Card.Body>
                  </Card>
                  <Card className="bg-warning card-item-width">
                    <Image src={logo} />
                    <Card.Body>
                      My Second Card
                      <Button>
                        Buy Now <FontAwesomeIcon icon={faCartPlus} />
                      </Button>
                    </Card.Body>
                  </Card>
                  <Card className="bg-warning card-item-width">
                    <Image src={logo} />
                    <Card.Body>
                      My Third Card
                      <Button>
                        Buy Now <FontAwesomeIcon icon={faCartPlus} />
                      </Button>
                    </Card.Body>
                  </Card>
                  <Card className="bg-warning card-item-width">
                    <Image src={logo} />
                    <Card.Body>
                      My Fourth Card
                      <Button>
                        Buy Now <FontAwesomeIcon icon={faCartPlus} />
                      </Button>
                    </Card.Body>
                  </Card>
                </CardGroup>
              </Carousel.Item>
              <Carousel.Item>
                <CardGroup>
                  <Card className="bg-warning card-item-width">
                    <Card.Img variant="top" src={logo} />
                    <Card.Body>
                      <Placeholder as={Card.Title} animation="glow">
                        <Placeholder xs={6} />
                      </Placeholder>
                      <Placeholder.Button xs={6} animation="glow" />
                    </Card.Body>
                  </Card>
                  <Card className="bg-warning card-item-width">
                    <Image src={logo} />
                    <Card.Body>
                      My Second Card
                      <Button>
                        Buy Now <FontAwesomeIcon icon={faCartPlus} />
                      </Button>
                    </Card.Body>
                  </Card>
                  <Card className="bg-warning card-item-width">
                    <Image src={logo} />
                    <Card.Body>
                      My Third Card
                      <Button>
                        Buy Now <FontAwesomeIcon icon={faCartPlus} />
                      </Button>
                    </Card.Body>
                  </Card>
                  <Card className="bg-warning card-item-width">
                    <Image src={logo} />
                    <Card.Body>
                      My Fourth Card
                      <Button>
                        Buy Now <FontAwesomeIcon icon={faCartPlus} />
                      </Button>
                    </Card.Body>
                  </Card>
                </CardGroup>
              </Carousel.Item>
            </Carousel>
          </Col>
        </Row>
      </div>
    );
  }
}
