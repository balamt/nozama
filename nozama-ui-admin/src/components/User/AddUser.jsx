import React, { useState } from "react";
import {
  Container,
  Row,
  Col,
  Form,
  FormControl,
  FloatingLabel,
  Alert,
  Tabs,
  Tab,
  ProgressBar,
  Button,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import {
  ADDRESS_CITY,
  ADDRESS_COUNTRY,
  ADDRESS_LINE1,
  ADDRESS_LINE2,
  ADDRESS_STATE,
  ADDRESS_STREET,
  ADDRESS_TYPE,
  ADDRESS_PINCODE,
} from "../../services/common/AddressRequest";
import UserRequest, {
  USER_EMAIL,
  USER_FULLNAME,
  USER_PASSWORD,
  USER_MOBILE,
  USER_TYPE,
  USER_GENDER,
} from "../../services/common/UserRequest";
import UserService from "../../services/UserService";
import UserPreviewCard from "./UserPreviewCard";

const AddUser = () => {
  const navigate = useNavigate();
  const [showAlert, setShowAlert] = useState(true);
  const [validated, setValidated] = useState(false);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [uploadProgressShow, setUploadProgressShow] = useState(false);

  //Storing the entire User Object into the state.
  const [state, setState] = useState({ user: UserRequest.getUser() });

  const addUserHandler = async (e) => {
    const form = e.currentTarget;
    if (form.checkValidity() === false) {
      e.preventDefault();
      e.stopPropagation();
    }
    setValidated(true);
    e.preventDefault();

    if (await UserService.addUser(state.user)) {
      navigate("/user/add");
    }
  };
  const userImageUploadHandler = () => {};

  // Update the state value of user
  const updateUser = (column, value, type = "string") => {
    let usr = state.user;
    usr[column] = value;
    //if the data type is number, then parse it
    if (type === "number") {
      usr[column] = parseInt(value);
    }

    setState({ ...state, user: usr });
  };

  const updateAddress = (column, value, type = "string") => {
    let adr = state.user.address;
    adr[column] = value;
    //if the data type is number, then parse it
    if (type === "number") {
      adr[column] = parseInt(value);
    }

    let usr = state.user;
    usr.address = adr;
    setState({ ...state, user: usr });
    console.log(usr);
  };

  return (
    <div className="mb-5">
      <Form noValidate validated={validated} onSubmit={addUserHandler}>
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
            <li>Fill in all the details and click Add User.</li>
            <li>Do not close or reload, your details will not be saved.</li>
            <li>Upload image of 2 MB, not more than that.</li>
            <li>Image must be Square (1:1 ratio).</li>
          </ul>
        </Alert>
        <Row className="mb-1">
          <Col xs={0} sm={5} lg={3}>
            <UserPreviewCard user={state} />
          </Col>
          <Col>
            <Tabs defaultActiveKey="user-tab-1" id="user-tabs">
              <Tab eventKey="user-tab-1" title="Basic Details">
                <Container fluid className="my-3">
                  <Row>
                    <Col xs={15} sm={11} lg={6}>
                      <Form.Group sm={6} className="m-1">
                        <FloatingLabel label="Full Name" className="mb-3">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Full Name"
                            onChange={(e) => {
                              updateUser(USER_FULLNAME, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel
                          className="mb-3"
                          label="User Email/UserName"
                        >
                          <Form.Control
                            required
                            type="text"
                            placeholder="User Email/UserName"
                            onChange={(e) => {
                              updateUser(USER_EMAIL, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel label="Password" className="mb-3">
                          <Form.Control
                            required
                            type="password"
                            placeholder="Password"
                            onChange={(e) => {
                              updateUser(USER_PASSWORD, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                      </Form.Group>
                    </Col>
                    <Col xs={15} sm={11} lg={6}>
                      <Form.Group>
                        <FloatingLabel className="mb-3" label="Mobile">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Mobile"
                            onChange={(e) => {
                              updateUser(USER_MOBILE, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <div key={`inline-radio`} className="mb-3">
                          <div>Gender</div>
                          <Form.Check
                            inline
                            label="Male"
                            name="gender"
                            type="radio"
                            id={`MALE`}
                            onChange={(e) => {
                              updateUser(USER_GENDER, e.target.id);
                            }}
                          />
                          <Form.Check
                            inline
                            label="Female"
                            name="gender"
                            type="radio"
                            id={`FEMALE`}
                            onChange={(e) => {
                              updateUser(USER_GENDER, e.target.id);
                            }}
                          />
                          <Form.Check
                            inline
                            name="gender"
                            label="Not Disclosed"
                            type="radio"
                            id={`NOT_DISCLOSED`}
                            onChange={(e) => {
                              updateUser(USER_GENDER, e.target.id);
                            }}
                          />
                        </div>

                        <div key={`usertype-inline-radio`} className="mb-3">
                          <div>User Type</div>
                          <Form.Check
                            inline
                            label="ADMIN"
                            name="usertype"
                            type="radio"
                            id={`ADMIN`}
                            onChange={(e) => {
                              updateUser(USER_TYPE, e.target.id);
                            }}
                          />
                          <Form.Check
                            inline
                            label="BASIC"
                            name="usertype"
                            type="radio"
                            id={`BASIC`}
                            onChange={(e) => {
                              updateUser(USER_TYPE, e.target.id);
                            }}
                          />
                          <Form.Check
                            inline
                            name="usertype"
                            label="SELLER"
                            type="radio"
                            id={`SELLER`}
                            onChange={(e) => {
                              updateUser(USER_TYPE, e.target.id);
                            }}
                          />
                        </div>
                        <FloatingLabel className="mb-3 p-2" label="User Image">
                          <Form.Control
                            accept="image/png, image/jpeg"
                            type="file"
                            alt="User Image"
                            onChange={userImageUploadHandler}
                          />
                        </FloatingLabel>
                      </Form.Group>
                    </Col>
                  </Row>
                </Container>
              </Tab>
              <Tab eventKey="user-tab-2" title="User Address">
                <Container fluid className="my-3">
                  <Row>
                    <Col xs={15} sm={11} lg={6}>
                      <Form.Group sm={6} className="m-1">
                        <FloatingLabel label="Address Line 1" className="mb-3">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Address Line 1"
                            onChange={(e) => {
                              updateAddress(ADDRESS_LINE1, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel className="mb-3" label="Address Line 2">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Address Line 2"
                            onChange={(e) => {
                              updateAddress(ADDRESS_LINE2, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel label="Street" className="mb-3">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Street"
                            onChange={(e) => {
                              updateAddress(ADDRESS_STREET, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel label="City" className="mb-3">
                          <Form.Control
                            required
                            type="text"
                            placeholder="City"
                            onChange={(e) => {
                              updateAddress(ADDRESS_CITY, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                      </Form.Group>
                    </Col>
                    <Col xs={15} sm={11} lg={6}>
                      <Form.Group>
                        <FloatingLabel className="mb-3" label="State">
                          <Form.Control
                            required
                            type="text"
                            placeholder="State"
                            onChange={(e) => {
                              updateAddress(ADDRESS_STATE, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel className="mb-3" label="Country">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Country"
                            onChange={(e) => {
                              updateAddress(ADDRESS_COUNTRY, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <FloatingLabel className="mb-3" label="Pincode">
                          <Form.Control
                            required
                            type="text"
                            placeholder="Pincode"
                            onChange={(e) => {
                              updateAddress(ADDRESS_PINCODE, e.target.value);
                            }}
                          />
                        </FloatingLabel>
                        <div key={`addresstype-inline-radio`} className="mb-3">
                          <div>Address Type</div>
                          <Form.Check
                            inline
                            label="Home"
                            name={ADDRESS_TYPE}
                            type="radio"
                            id={`HOME`}
                            onChange={(e) => {
                              updateAddress(ADDRESS_TYPE, e.target.id);
                            }}
                          />
                          <Form.Check
                            inline
                            label="Warehouse"
                            name={ADDRESS_TYPE}
                            type="radio"
                            id={`WAREHOUSE`}
                            onChange={(e) => {
                              updateAddress(ADDRESS_TYPE, e.target.id);
                            }}
                          />
                          <Form.Check
                            inline
                            name={ADDRESS_TYPE}
                            label="Other"
                            type="radio"
                            id={`OTHER`}
                            onChange={(e) => {
                              updateAddress(ADDRESS_TYPE, e.target.id);
                            }}
                          />
                        </div>
                      </Form.Group>
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
            <Button onClick={addUserHandler} className="m-3" variant="primary">
              Add User
            </Button>
          </Col>
        </Row>
      </Form>
    </div>
  );
};

export default AddUser;
