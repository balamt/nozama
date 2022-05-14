import React from "react";
import {
  Button,
  Form,
  Col,
  Row,
  FormControl,
  Tabs,
  Container,
  FloatingLabel,
  Alert,
  ProgressBar,
} from "react-bootstrap";

import { FaSearch } from "react-icons/fa";
import Product from "./Product";
import ProductPreviewCard from "./ProductPreviewCard";

function EditProduct() {
  return (
    <Container fluid className="mb-5">
      <Row className="mt-3">
        <Form className="d-flex">
          <Col>
            <FormControl
              type="search"
              placeholder="Search Product(s) by id or name. Use (id=123 or name=blue jeans) for quicker and accurate result"
              className="me-2"
              aria-label="Search Product(s) by id or name"
            />
            <sup className="mx-2 text-danger">Use (id=123 or name=blue jeans) for quicker and accurate results.</sup>
          </Col>
          <Col sm={1} lg={1}>
            <Button className="btn btn-warning">
              <FaSearch size={28} />
            </Button>
          </Col>
        </Form>
      </Row>
      <Row>
        <Col sm={5} lg={3}>
          <ProductPreviewCard />
        </Col>
        <Col>
          {/* <Product /> */}
        </Col>
      </Row>
    </Container>
  );
}

export default EditProduct;
