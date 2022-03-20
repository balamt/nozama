import React from "react";
import "jquery";

import logo from "./img/logo.png";
import { Carousel } from "react-bootstrap";
import { CarouselItem } from "react-bootstrap";

export class Slider extends React.Component {
  //   constructor(props) {
  //     super(props);
  //   }

  render() {
    return (
      <div className="container bg-info">
        <div className="row" style={{ justifyContent: "center" }}>
          <div className="col-sm-12">
            <Carousel>
              <Carousel.Item>
                <div className="card bg-secondary" style={{ width: "18rem" }}>
                  <img className="card-img-top" src={logo} alt="Card cap" />
                  <div className="card-body">
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">
                      Some quick example text to build on the card title and
                      make up the bulk of the card's content.
                    </p>
                    <a href="/#" className="btn btn-primary">
                      Go somewhere
                    </a>
                  </div>
                </div>
              </Carousel.Item>
              <Carousel.Item>
                <div className="card bg-secondary" style={{ width: "18rem" }}>
                  <img className="card-img-top" src={logo} alt="Card cap" />
                  <div className="card-body">
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">
                      Some quick example text to build on the card title and
                      make up the bulk of the card's content.
                    </p>
                    <a href="/#" className="btn btn-primary">
                      Go somewhere
                    </a>
                  </div>
                </div>
              </Carousel.Item>
            </Carousel>
          </div>
        </div>
      </div>
    );
  }
}
