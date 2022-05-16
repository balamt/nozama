import React, { useState } from "react";
import {
  Container,
  Row,
  Col,
  FormControl,
  Form,
  Button,
} from "react-bootstrap";
import { FaSearch } from "react-icons/fa";
import UserRequest from "../../services/common/UserRequest";
import UserService from "../../services/UserService";

const Users = () => {
  const [search, setSearch] = useState("");
  const [user, setUser] = useState(UserRequest.getUser());

  const handleSearchUser = (e) => {
    if (e.target.value) {
      setSearch(e.target.value);
    }

    if (e.code === "Enter") {
      console.log("Enter pressed");
      e.preventDefault();
      //Trigger the service call to search the user
      let query = search.split("=");
      if (query[0] === "email") {
        console.log(query[1]);
        setUser(UserService.findUserByUserName(query[1]));
        console.log(user);
      } else if (query[0] === "id") {
        setUser(UserService.findUserById(parseInt(query[1])));
        console.log(user);
      } else { 
        //TODO: Need to implement global search in API and implement the service call in the UserService.jsx
        console.log(UserService.findUserByIdOrUserName(search));
      }
    }
  };

  return (
    <Container fluid className="mb-5">
      <Row className="mt-3">
        <Form className="d-flex">
          <Col>
            <FormControl
              required
              type="search"
              placeholder="Search User(s) by id, name, email or phone."
              className="me-2"
              aria-label="Search User(s) by id or name"
              onChange={handleSearchUser}
              onKeyDown={handleSearchUser}
            />
            <sup className="mx-2 text-danger">
              Use id = userid , name = fullname , email = email address or phone
              = phonenumber for quicker and accurate result
            </sup>
          </Col>
          <Col sm={1} lg={1}>
            <Button onClick={handleSearchUser} className="btn btn-warning">
              <FaSearch size={28} />
            </Button>
          </Col>
        </Form>
      </Row>
    </Container>
  );
};

export default Users;
