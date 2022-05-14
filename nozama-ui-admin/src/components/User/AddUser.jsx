import React, { useState } from "react";
import { Container, Row, Col, FormControl } from "react-bootstrap";
import UserRequest, { USER_ID } from "../../services/common/UserRequest";

const AddUser = () => {
  //Storing the entire User Object into the state.
  const [state, setState] = useState({ user: UserRequest.getUser() });

  // Update the state value of user
  const updateUser = (column, value, type = "string") => {
    let usr = state.user;
    usr[column] = value;
    //if the data type is number, then parse it
    if (type === "number") {
      usr[column] = parseInt(value);
    }

    console.log(usr);
    setState({ ...state, user: usr });
  };
  return (
    <Container fluid className="mb-5">
      <Row>
        <Col>Add User</Col>
        <Col>
          <FormControl
            type="text"
            placeholder="Test"
            onChange={(e) => {
              updateUser(USER_ID, e.target.value);
            }}
          />
        </Col>
      </Row>
    </Container>
  );
};

export default AddUser;
