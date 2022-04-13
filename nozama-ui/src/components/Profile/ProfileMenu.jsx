import React from "react";

function ProfileMenu(props) {
  return (
    <div style={props.loggedin ? { display: "none" } : {}}>
      <a className="dropdown-item" href="/login">
        Sign in
      </a>
      <div className="dropdown-divider"></div>
      <a className="dropdown-item" href="/signup">
        Sign Up
      </a>
      <div className="dropdown-divider"></div>
      <a className="dropdown-item" href="/login">
        My Account
      </a>
    </div>
  );
}

export default ProfileMenu;
