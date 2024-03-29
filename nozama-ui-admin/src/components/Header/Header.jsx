import React from "react";
import {
  Container,
  Form,
  FormControl,
  Nav,
  Navbar,
  NavDropdown,
  Button,
} from "react-bootstrap";
import { FcShop, FcSearch } from "react-icons/fc";
import { FaSearch } from "react-icons/fa";
import { Link, NavLink } from "react-router-dom";
import {
  ROUTE_EDIT_PRODUCT,
  ROUTE_PRODUCT,
  ROUTE_ADD_USER,
  ROUTE_EDIT_USER,
} from "../../AdminUIConst";

function Header() {
  return (
    <Navbar expand="lg" className="bg-primary">
      <Container fluid>
        <Navbar.Brand as={NavLink} to="/">
          <FcShop size={52} />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: "100px", fontSize: "1.2em" }}
          >
            <Nav.Link as={NavLink} to="/">
              Home
            </Nav.Link>
            <NavDropdown title="Products" id="navbarScrollingDropdown">
              <NavDropdown.Item as={NavLink} to={ROUTE_PRODUCT}>
                Add Product
              </NavDropdown.Item>
              <NavDropdown.Item as={NavLink} to={ROUTE_EDIT_PRODUCT}>
                Edit Product
              </NavDropdown.Item>
              <NavDropdown.Item as={NavLink} to="/products#remove">
                Remove Products
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item as={NavLink} to="/products#view">
                Find Products
              </NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="Users" id="navbarScrollingDropdown">
              <NavDropdown.Item as={NavLink} to={ROUTE_ADD_USER}>
                Add User
              </NavDropdown.Item>
              <NavDropdown.Item as={NavLink} to={ROUTE_EDIT_USER}>
                Edit User
              </NavDropdown.Item>
              <NavDropdown.Item as={NavLink} to="/user#remove">
                Remove User
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item as={NavLink} to="/user">
                Find User
              </NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="Orders" id="navbarScrollingDropdown">
              <NavDropdown.Item as={NavLink} to="/order#modify">
                Modify Orders
              </NavDropdown.Item>
              <NavDropdown.Item as={NavLink} to="/order#remove">
                Remove Orders
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item as={NavLink} to="/order#view">
                View Orders
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Form className="d-flex">
            <FormControl
              type="search"
              placeholder="Search"
              className="me-2"
              aria-label="Search"
            />
            <Button className="btn btn-secondary">
              <FaSearch size={28} />
            </Button>
          </Form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Header;
