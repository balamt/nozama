import React from "react";
import { Form, Button } from "react-bootstrap";
import { useState } from "react";
import AuthService from "./AuthService";

import "../css/Login.css";

const Login = (props) => {
  // const [username, password, setUserName, setPassword] = useState({
  //   email: "",
  //   password: "",
  // });

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

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

    const json = {
      email: email,
      password: password,
    };
    // Array.from(data.entries()).forEach(([key, value]) => {
    //   json[key] = value;
    // });
    console.log(json);
    console.log(AuthService.login(email, password));
    // const requestOptions = {
    //   method: "POST",
    //   headers: { "Content-Type": "application/json" },
    //   body: JSON.stringify(json), /////here is the change
    //   mode: "cors",
    // };
    // fetch("http://localhost:8090/auth/token", requestOptions)
    //   .then((response) => response.json())
    //   .then((data) => {
    //     console.log(data.token);
    //   });
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
        <Form.Control
          required
          type="email"
          placeholder="Enter User Name"
          onChange={(e) => {
            setEmail(e.target.value);
          }}
        />
        <Form.Text className="text-info">Email id is your username.</Form.Text>
        <Form.Control.Feedback type="invalid">
          Username is Required to Login.
        </Form.Control.Feedback>
      </Form.Group>
      <Form.Group className="mb-3" controlId="password">
        <Form.Label>Password</Form.Label>
        <Form.Control
          required
          type="password"
          placeholder="Password"
          onChange={(e) => {
            setPassword(e.target.value);
          }}
        />
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
