import React from "react";
import { Form, Button } from "react-bootstrap";
import { useState } from "react";

import "./css/Login.css";

const Login = (props) => {
  // const [username, password, setUserName, setPassword] = useState({
  //   email: username,
  //   password: password,
  // });

  const [validated, setValidated] = useState(false);
  // Login Request is failing to populate the JSON request data.
  // CORS issue, Need to fix in Server Code.
  const loginHandler = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    setValidated(true);
    event.preventDefault();
    const data = new FormData(event.target);
    const json = {};
    Array.from(data.entries()).forEach(([key, value]) => {
      json[key] = value;
    });

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(json), /////here is the change
      mode: "cors",
    };
    fetch("http://localhost:8090/auth/token", requestOptions).then((response) =>
      console.log(response.json())
    );
  };

  return (
    <Form
      className="needs-validation bg-dark text-light"
      noValidate
      validated={validated}
      onSubmit={loginHandler}
    >
      <Form.Group className="mb-3" controlId="email">
        <Form.Label>Username</Form.Label>
        <Form.Control required type="email" placeholder="Enter User Name" />
        <Form.Text className="text-info">Email id is your username.</Form.Text>
        <Form.Control.Feedback type="invalid">
          Username is Required to Login.
        </Form.Control.Feedback>
      </Form.Group>
      <Form.Group className="mb-3" controlId="password">
        <Form.Label>Password</Form.Label>
        <Form.Control required type="password" placeholder="Password" />
        <Form.Text className="text-info">
          Password Must be 8-20 characters long.
        </Form.Text>
        <Form.Control.Feedback type="invalid">
          Username is Required to Login.
        </Form.Control.Feedback>
      </Form.Group>
      <Form.Group className="mb-3" controlId="remember">
        <Form.Check type="switch" label="Remember Me" />
      </Form.Group>
      <Button className="btn btn-secondary" variant="primary" type="submit">
        Login
      </Button>
    </Form>
  );
};

export default Login;
