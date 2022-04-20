import React from "react";
import { Link } from "react-router-dom";

import Logout from "../Logout/Logout";

function SecureProfileMenu(props) {
  return (
    <div style={props.loggedin ? {} : { display: "none" }}>
      <Link to="/account" className="dropdown-item">
        My Account
      </Link>
      <div className="dropdown-divider"></div>
      {<Logout />}
    </div>
  );
}

export default SecureProfileMenu;
