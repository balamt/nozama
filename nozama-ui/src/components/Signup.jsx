import React from "react";
import { Form, Button } from "react-bootstrap";
import { useState } from "react";

const Signup = (props) => {
  //const [username, password, setUserName, setPassword] = useState(props.username, props.password);

  return (
    <div className="Login-Form">
      <div className="d-flex align-items-center">
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Username</Form.Label>
            <Form.Control type="email" placeholder="Enter User Name" />
            <Form.Text className="text-muted">
              Username is your email id.
            </Form.Text>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicCheckbox">
            <Form.Check type="checkbox" label="Check me out" />
          </Form.Group>
          <Button className="btn btn-secondary" variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </div>
    </div>
  );
};

export default Signup;
