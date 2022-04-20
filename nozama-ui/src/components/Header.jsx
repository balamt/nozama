import React from "react";
import "jquery";
import { Button, Badge } from "react-bootstrap";
import { FaShoppingCart, FaSearch, FaUserCircle } from "react-icons/fa";
import "../components/css/NozamaApp.css";
import { Row } from "react-bootstrap";
import { Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { ReactCountryFlag } from "react-country-flag";
import { useSelector } from "react-redux";
import {
  getToken,
  getTokenExpiryInMinutes,
  getTokenValidTo,
  isTokenExpired,
  isUserLoggedIn,
} from "../features/login/loginSlice";
import ProfileMenu from "./Profile/ProfileMenu";
import SecureProfileMenu from "./Profile/SecureProfileMenu";

// Functional Component, with props
function Header(props) {
  /**
   * To Redirect to another context path
   **/
  let navigate = useNavigate();
  const openCart = () => {
    navigate("/cart");
  };

  //const token = useSelector(getLogin);
  const token = useSelector(getToken);
  const tokenValidTo = useSelector(getTokenValidTo);
  const tokenExpired = useSelector(isTokenExpired);
  const expiringIn = useSelector(getTokenExpiryInMinutes);
  const userLoggedIn = useSelector(isUserLoggedIn);

  return (
    <React.Fragment>
      <header>
        <nav className="navbar fixed-top navbar-expand-lg navbar-dark bg-dark justify-content-between">
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div>
            <a className="navbar-brand" href="/">
              <img
                src={props.logo}
                width="45"
                height="45"
                className="d-inline-block align-top"
                alt={props.appName}
              />
              {props.appName}
            </a>
          </div>
          <div
            style={{
              width: "-webkit-fill-available",
            }}
            className="collapse navbar-collapse"
          >
            <form className="form-inline">
              <input
                className="form-control mr-sm-2 search-box-border"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button
                className="btn btn-secondary search-btn-border"
                type="submit"
              >
                <FaSearch size={24} className="text-primary" title="Search" />
              </button>
            </form>
          </div>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto navbar-expand-align">
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  href="/login"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  <ReactCountryFlag
                    svg
                    countryCode="IN"
                    style={{
                      width: "2em",
                      height: "2em",
                    }}
                    title="IN"
                  />
                </a>
                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a className="dropdown-item" href="/lang/us">
                    <ReactCountryFlag
                      svg
                      countryCode="US"
                      style={{
                        width: "2em",
                        height: "2em",
                      }}
                      title="US"
                    />
                    &nbsp; US
                  </a>
                  <div className="dropdown-divider"></div>
                  <a className="dropdown-item" href="/lang/mx">
                    <ReactCountryFlag
                      svg
                      countryCode="MX"
                      style={{
                        width: "2em",
                        height: "2em",
                      }}
                      title="MX"
                    />
                    &nbsp; MX
                  </a>
                  <div className="dropdown-divider"></div>
                  <a className="dropdown-item" href="/#">
                    Preferences
                  </a>
                </div>
              </li>
              <li className="nav-item">
                <Button className="btn btn-primary" onClick={openCart}>
                  <Row>
                    <Col
                      md="2"
                      sm="1"
                      className="d-flex"
                      style={{
                        alignItems: "baseline",
                        flexDirection: "column-reverse",
                      }}
                    >
                      <FaShoppingCart
                        size={30}
                        className="text-secondary"
                        title="Cart"
                        style={{
                          paddingBottom: "2px",
                        }}
                      />
                      <Badge
                        style={{
                          fontSize: "1.2em",
                          padding: "0px 0px 0px 6px",
                          color: "red",
                          fontWeight: "500",
                          fontFamily: "sans-serif",
                        }}
                      >
                        09
                      </Badge>

                      {/* <span className="visually-hidden">unread messages</span> */}
                    </Col>
                  </Row>
                </Button>
              </li>
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  href="/#"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  <FaUserCircle size={38} className="text-info" title="User" />
                </a>
                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                  <SecureProfileMenu loggedin={userLoggedIn} />
                  <ProfileMenu loggedin={userLoggedIn} />
                </div>
              </li>
              <li className="nav-item">
                <a className="nav-link disabled" href="/#">
                  &nbsp;&nbsp;&nbsp;&nbsp;
                </a>
              </li>
            </ul>
          </div>
        </nav>
      </header>
    </React.Fragment>
  );
}

export default Header;
