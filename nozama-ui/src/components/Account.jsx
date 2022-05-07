import React, { useEffect } from "react";
import Slider from "./Slider";
import { Stack, Ratio } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { isUserLoggedIn } from "../features/login/loginSlice";

const Account = (prop) => {
  let navigate = useNavigate();

  const userLoggedIn = useSelector(isUserLoggedIn);

  useEffect(() => {
    // Check if user is logged in, if not redirect to login page
    if (!userLoggedIn) {
      navigate("/login");
    }
  }, [userLoggedIn, navigate]);

  return <Stack gap={4}></Stack>;
};

export default Account;
