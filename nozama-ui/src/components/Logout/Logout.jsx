import React, { useEffect } from "react";
import { Button } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { logout } from "../../features/login/loginSlice";
import AuthService from "../Auth/AuthService";

function Logout() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  function handleLogout() {
    dispatch(logout());
    navigate("/login");
  }
  return (
    <Button className="dropdown-item" onClick={handleLogout}>
      Logout
    </Button>
  );
}

export default Logout;
