import React from "react";
import "jquery";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch, faCartShopping } from "@fortawesome/free-solid-svg-icons";
import "../components/css/NozamaApp.css";

// Functional Component, with props
function Header(props) {
  return (
    <React.Fragment>
      <header>
        <nav className="navbar fixed-top navbar-expand-lg navbar-dark bg-dark justify-content-between">
          <a class="navbar-brand" href="/#">
            <img
              src={props.logo}
              width="45"
              height="45"
              class="d-inline-block align-top"
              alt={props.appName}
            />
            {props.appName}
          </a>
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
              <FontAwesomeIcon
                size="lg"
                icon={faSearch}
                className="text-primary"
                title="Search"
              />
            </button>
          </form>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto navbar-expand-align">
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
                  Sign in
                </a>
                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a className="dropdown-item" href="/#">
                    Sign in
                  </a>
                  <div className="dropdown-divider"></div>
                  <a className="dropdown-item" href="/#">
                    Sign Up
                  </a>
                  <div className="dropdown-divider"></div>
                  <a className="dropdown-item" href="/#">
                    Preferences
                  </a>
                </div>
              </li>

              <li className="nav-item active">
                <a className="nav-link" href="/#">
                  <FontAwesomeIcon
                    size="lg"
                    icon={faCartShopping}
                    className="text-secondary"
                    title="Cart"
                  />{" "}
                  <span className="sr-only">(current)</span>
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/#">
                  Help
                </a>
              </li>
              {/* <li className="nav-item">
                <a className="nav-link disabled" href="/#">
                  Disabled
                </a>
              </li> */}
            </ul>
          </div>
        </nav>
      </header>
    </React.Fragment>
  );
}

export default Header;
