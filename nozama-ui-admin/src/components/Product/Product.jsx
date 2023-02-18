import React, { useEffect, useState } from "react";
import {
  Button,
  Form,
  Col,
  Row,
  Tab,
  Tabs,
  Container,
  FloatingLabel,
  Alert,
  ProgressBar,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import ProductRequest from "../../services/common/ProductRequest.jsx";
import ProductService from "../../services/ProductService.jsx";
import ProductPreviewCard from "./ProductPreviewCard.jsx";

import thumb_preview from "../../resources/images/image_thumb.svg";
import Category from "../Category/Category.jsx";

const Product = () => {
  const navigate = useNavigate();
  const [showAlert, setShowAlert] = useState(true);
  const [productName, setProductName] = useState("");
  const [productDescription, setProductDescription] = useState("");
  const [productCode, setProductCode] = useState("");
  const [productRating, setProductRating] = useState(0);
  const [category, setCategory] = useState("");
  const [productPricePerItem, setProductPricePerItem] = useState(0);
  const [stockQuantity, setStockQuantity] = useState(0);
  const [warehouse, setWarehouse] = useState(0);
  const [tags, setTags] = useState([]);
  const [productImage, setProductImage] = useState(thumb_preview);
  const [productImageFile, setProductImageFile] = useState(null);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [uploadProgressShow, setUploadProgressShow] = useState(false);

  const [validated, setValidated] = useState(false);

  const selectedCategoryCallBack = (cat) => {
    console.log(cat);
    if (cat && cat.length > 0 && cat[0].category) {
      setCategory(cat[0].category);
    } else if (cat && cat.length > 0) {
      setCategory(cat[0]);
    }
  };

  const addProductHandler = async (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    setValidated(true);
    event.preventDefault();
    let newproduct = ProductRequest.getProduct();
    newproduct.productName = productName;
    newproduct.productDescription = productDescription;
    newproduct.category = category;
    newproduct.pricePerItem = productPricePerItem;
    newproduct.stockQuantity = stockQuantity;
    newproduct.tags = tags;

    let responseProduct = ProductRequest.getProduct();
    responseProduct = await ProductService.addProduct(newproduct);
    console.log(responseProduct);
    if (responseProduct?.productId) {
      await ProductService.uploadProductImage(
        responseProduct,
        productImageFile,
        (event) => {
          let progress = Math.round((100 * event.loaded) / event.total);
          if (progress === 100) {
            setUploadProgressShow(false);
            setUploadProgress(0);
          } else {
            setUploadProgressShow(true);
            setUploadProgress(progress);
          }
        }
      );
    }
    navigate("/product");
  };

  const productImageUploadHandler = (e) => {
    setProductImageFile(e.target.files[0]);
    setProductImage(URL.createObjectURL(e.target.files[0]));
  };

  return (
    <div>
      <Form noValidate validated={validated} onSubmit={addProductHandler}>
        <Alert
          show={showAlert}
          key="warning"
          variant="warning"
          dismissible
          onClose={() => {
            setShowAlert(false);
          }}
        >
          <Alert.Heading>Attention</Alert.Heading>
          <ul>
            <li>Fill in all the details and click Save.</li>
            <li>Do not close or reload, your details will not be saved.</li>
            <li>Upload image of 2 MB, not more than that.</li>
            <li>Image must be Square (1:1 ratio).</li>
          </ul>
        </Alert>
        <Row className="mb-1">
          <Col xs={0} sm={5} lg={3}>
            <ProductPreviewCard
              name={productName}
              description={productDescription}
              rate={productPricePerItem}
              quantity={stockQuantity}
              pimage={productImage}
              tags={tags}
            />
          </Col>
          <Col>
            <Tabs defaultActiveKey="product-tab-1" id="product-tabs">
              <Tab eventKey="product-tab-1" title="Basic Details">
                <Container fluid>
                  <Row>
                    <Col xs={15} sm={11} lg={6}>
                      <Form.Group sm={6} className="m-1">
                        {/* <FloatingLabel className="mb-3" label="Category">
                          <Form.Select
                            className="bg-white text-gray-400"
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
                        </FloatingLabel> */}
                        <Category
                          getSelectedCategory={selectedCategoryCallBack}
                        />
                        <FloatingLabel label="Product Name" className="mb-3">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Product Name"
                            onChange={(e) => {
                              setProductName(e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel
                          className="mb-3"
                          label="Product Description"
                        >
                          <Form.Control
                            required
                            as="textarea"
                            placeholder="Product Description"
                            onChange={(e) => {
                              setProductDescription(e.target.value);
                            }}
                          />
                        </FloatingLabel>

                        <FloatingLabel className="mb-3" label="Product Code">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Product Code"
                            onChange={(e) => {
                              setProductCode(e.target.value);
                            }}
                          />
                        </FloatingLabel>
                      </Form.Group>
                    </Col>
                    <Col xs={15} sm={11} lg={6}>
                      <Form.Group>
                        <FloatingLabel
                          className="mb-3"
                          label="Product Price Per Item"
                        >
                          <Form.Control
                            required
                            type="currency"
                            placeholder="Product Price Per Item"
                            onChange={(e) => {
                              setProductPricePerItem(e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel
                          label="Available Quantity"
                          className="mb-3"
                        >
                          <Form.Control
                            required
                            type="number"
                            placeholder="Available Quantity"
                            onChange={(e) => {
                              setStockQuantity(parseInt(e.target.value));
                            }}
                          />
                        </FloatingLabel>

                        <FloatingLabel className="mb-3" label="Warehouse Id">
                          <Form.Control
                            required
                            type="number"
                            placeholder="Warehouse"
                            onChange={(e) => {
                              setWarehouse(e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel
                          className="mb-3 p-2"
                          label="Product Image"
                        >
                          <Form.Control
                            accept="image/png, image/jpeg"
                            type="file"
                            alt="Product Image"
                            onChange={productImageUploadHandler}
                          />
                        </FloatingLabel>
                      </Form.Group>
                    </Col>
                    <Col>
                      <FloatingLabel className="mb-3 p-2" label="Tags">
                        <Form.Control
                          required
                          as="textarea"
                          placeholder="Tags (Separate with Comma)"
                          onKeyUp={(e) => {
                            if (e.key === ",") {
                              //Splitting the value by , and ignoring the empty text using filter
                              setTags(
                                e.target.value.split(",").filter((tag) => tag)
                              );
                            }
                          }}
                        />
                      </FloatingLabel>
                    </Col>
                  </Row>
                </Container>
              </Tab>
            </Tabs>
          </Col>
        </Row>
        <Row className="mb-5">
          <Col>
            {uploadProgressShow ? (
              <ProgressBar animated now={uploadProgress} />
            ) : (
              ""
            )}
          </Col>
          <Col>
            <Button variant="danger">Close</Button>
            <Button
              onClick={addProductHandler}
              className="m-3"
              variant="primary"
            >
              Save changes
            </Button>
          </Col>
        </Row>
      </Form>
    </div>
  );
};

export default Product;
