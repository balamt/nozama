import React, { useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "jquery";
import "popper.js";
import "bootstrap/dist/js/bootstrap.bundle.min";

import logo from "./components/img/logo.png";
import config from "./_config.app.json";
//Base Theme must be at the top of All other Stylesheets
import "./components/css/BaseTheme.css";

import "./components/css/NozamaApp.css";
import Header from "./components/Header.jsx";
import { Footer, SecureFooter } from "./components/Footer.jsx";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import {
  Account,
  Cart,
  Login,
  Signup,
  Product,
  Help,
  NotFound,
} from "./components/pages";
import Slider from "./components/Slider.jsx";
import { Stack, Ratio } from "react-bootstrap";
import { Navigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchAsyncProducts,
  getAllProducts,
} from "./features/product/productSlice";
import ProductDetails from "./components/Products/ProductDetails";

function NozamaApp(props) {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchAsyncProducts());
  }, [dispatch]);

  return (
    <Router>
      <Header logo={logo} appName={config.app.name} />
      <div className="container">
        <main style={{ marginTop: "110px" }}>
          <div className="row" style={{ justifyContent: "center" }}>
            <Routes>
              <Route path="404" element={<NotFound />} />
              <Route path="*" element={<Navigate replace to="/404" />} />
              <Route
                path="/"
                element={
                  <div>
                    <Slider />
                  </div>
                }
              />
              <Route path="/account" element={<Account />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/help" element={<Help />} />
              <Route path="/login" element={<Login />} />
              <Route path="/signin" element={<Login />} />
              <Route path="/product" element={<Product />} />
              <Route
                path="/product/:productId"
                element={<ProductDetails />}
                children={ProductDetails}
              />
              <Route path="/signup" element={<Signup />} />
            </Routes>
          </div>
        </main>
      </div>
      <SecureFooter copyrightYear={config.app.copyright_year} />
    </Router>
  );
}

export default NozamaApp;
