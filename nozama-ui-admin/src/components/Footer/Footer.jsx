import React from "react";
import { Container } from "react-bootstrap";
import { FaRegCopyright } from "react-icons/fa";

function Footer() {
  return (
    <Container fluid className="bg-primary fixed-bottom">
      Product of balamt.in &nbsp;&nbsp; <FaRegCopyright />
      &nbsp;&nbsp; {" " + new Date().getFullYear()}
    </Container>
  );
}

export default Footer;
