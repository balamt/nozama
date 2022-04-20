import React from "react";
import { Link } from "react-router-dom";

function ProfileMenu(props) {
  return (
    <div style={props.loggedin ? { display: "none" } : {}}>
      <Link to="/login" className="dropdown-item">
        Sign in
      </Link>
      <div className="dropdown-divider"></div>
      <Link to="/signup" className="dropdown-item">
        Sign Up
      </Link>
      <div className="dropdown-divider"></div>
      <Link to="/account" className="dropdown-item">
        My Account
      </Link>
    </div>
  );
}

export default ProfileMenu;
