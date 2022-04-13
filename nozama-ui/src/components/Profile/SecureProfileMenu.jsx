import React from "react";
import Logout from "../Logout/Logout";

function SecureProfileMenu(props) {
  return (
    <div style={props.loggedin ? {} : { display: "none" }}>
      <a className="dropdown-item" href="/#">
        My Account
      </a>
      <div className="dropdown-divider"></div>
      {<Logout />}
    </div>
  );
}

export default SecureProfileMenu;
