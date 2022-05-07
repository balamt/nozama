import React, { useState } from "react";
import { Button, Form, FormGroup, Modal, Tab, Tabs } from "react-bootstrap";

const Product = () => {
  const [show, setShow] = useState(false);
  const [productName, setProductName] = useState("");
  const [productDescription, setProductDescription] = useState("");
  const [productCode, setProductCode] = useState("");
  const [productRating, setProductRating] = useState(0);
  const [category, setCategory] = useState("");

  const [validated, setValidated] = useState(false);

  const closeModal = (event) => { 
    
  };

  const addProductHandler = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    setValidated(true);
    event.preventDefault();
  };

  return (
    <div>
      <div className="flex-col">
        <h2 className="text-2xl p-2">Products</h2>
        <div className="flex justify-start p-2">
          <div className="p-2 border-2 w-full">
            <Button
              className="btn bg-green-600 text-white p-2 m-1 font-light"
              name="Add Product"
              onClick={() => setShow(true)}
            >
              Add Product
            </Button>
            <Button
              className="btn bg-yellow-500 text-white p-2 m-1 font-light"
              name="Find Product"
            >
              Find Product
            </Button>
            <Button
              className="btn bg-red-600 text-white p-2 m-1 font-light"
              name="Remove Product"
            >
              Remove Product
            </Button>
          </div>
        </div>
      </div>
      <Modal
        show={show}
        fullscreen
        onHide={() => setShow(false)}
        aria-labelledby="example-custom-modal-styling-title"
      >
        <Modal.Header closeButton closeLabel="Dont Add/Save">
          <Modal.Title id="example-custom-modal-styling-title">
            Add New Product
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form noValidate validated={validated} onSubmit={addProductHandler}>
            <Tabs
              defaultActiveKey="product-tab-1"
              id="product-tabs"
              className="mb-3"
            >
              <Tab eventKey="product-tab-1" title="Basic Details">
                <Form.Group className="m-3">
                  <Form.Label className="p-2">Product Name</Form.Label>
                  <Form.Control
                    className="p-2"
                    required
                    type="text"
                    placeholder="Product Name"
                    onChange={(e) => {
                      setProductName(e.target.value);
                    }}
                  />
                  <Form.Label className="p-2">Product Code</Form.Label>
                  <Form.Control
                    className="p-2"
                    required
                    type="text"
                    placeholder="Product Code"
                    onChange={(e) => {
                      setProductCode(e.target.value);
                    }}
                  />
                  <Form.Label className="p-2">Category</Form.Label>
                  <Form.Select
                    className="p-2 bg-white text-gray-400"
                    aria-label="Category"
                    onChange={(e) => {
                      setCategory(e.target.value);
                    }}
                  >
                    <option>Select the Category</option>
                    <option value="CLOTHING">CLOTHING</option>
                    <option value="GROCERIES">GROCERIES</option>
                    <option value="APPS">APPS</option>
                  </Form.Select>
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label className="p-2">Product Description</Form.Label>
                  <Form.Control
                    className="p-2"
                    required
                    type="text"
                    placeholder="Product Description"
                    onChange={(e) => {
                      setProductDescription(e.target.value);
                    }}
                  />
                  <Form.Label className="p-2">Product Rating</Form.Label>
                  <Form.Control
                    className="p-2"
                    required
                    min={0}
                    max={5}
                    type="number"
                    placeholder="Product Rating"
                    onChange={(e) => {
                      setProductRating(e.target.value);
                    }}
                  />
                </Form.Group>
              </Tab>
              <Tab eventKey="product-tab-2" title="Tags, Seller, Stock"></Tab>
              <Tab eventKey="product-tab-3" title="Image & Preview"></Tab>
            </Tabs>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary">Close</Button>
          <Button variant="primary">Save changes</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default Product;
