import { Link } from "next/link";
import Image from "next/image";
import { FaSearch, FaShoppingCart, FaUserAlt } from "react-icons/fa";
import { React, useState, useEffect } from "react";
import "jquery";
import { Form, Button, Badge, Row, Col } from "react-bootstrap";
import ReactCountryFlag from "react-country-flag";

// Functional Component, with props
export default function Header(props) {
  const [user] = useState();

  /**
   * To Redirect to another context path
   **/
  // let navigate = useNavigate();
  const openCart = () => {
    navigate("/cart");
  };

  return (
    <div className="bg-primary z-50 h-16 flex items-center p-2 shadow-md top-0 sticky">
      {
        // Logo and App Name
      }

      <div className="flex flex-shrink-0 items-center justify-evenly px-2 mr-2">
        <Image src={props.appLogo} alt={props.appName} width={40} height={40} />
        <h1 className="hidden text-white text-l items-center px-2 sm:hidden md:hidden lg:block">
          {props.appName}
        </h1>
      </div>
      {
        //Search Box and Button
      }
      <div className="flex form-inline items-center">
        <input
          className="form-control mr-0 search-box-border"
          type="search"
          placeholder="Search"
          aria-label="Search"
        />
        <Button
          className="btn btn-secondary ml-0 search-btn-border"
          type="submit"
        >
          <FaSearch fontSize={20} />
        </Button>
      </div>
      {
        //Menu [Login/Signup/Logout],[Cart icon with Bagde], [Orders & Accounts|Profile Icon]
      }
      <div className="flex flex-grow justify-center mx-2">
        <div className="flex items-center justify-evenly">
          <div className="flex items-center flex-shrink-0 justify-start h-14 px-4 md:px-10  cursor-pointer">
            <ReactCountryFlag
              style={{
                fontSize: "2em",
                lineHeight: "1em",
              }}
              countryCode="IN"
              svg
            />
          </div>
        </div>
        <div className="flex items-center justify-evenly">
          <Badge
            className="flex items-end"
            style={{
              position: "absolute",
              fontSize: "1em",
              paddingTop: "2px",
              paddingRight: "2px",
            }}
            text="info"
            bg="none"
          >
            {0}
          </Badge>
          <Button
            className="btn bg-primary flex justify-around px-2 text-white hover:text-warning"
            type="submit"
          >
            <FaShoppingCart fontSize={40} className="mr-0" />
          </Button>
        </div>

        <div className="flex items-center justify-evenly">
          <div className="flex items-center flex-shrink-0 justify-start h-14 px-4 md:px-10  cursor-pointer">
            <FaUserAlt fontSize={26} className="text-white mr-0" />
          </div>
        </div>
      </div>
    </div>
  );
}
