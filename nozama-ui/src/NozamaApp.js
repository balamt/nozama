import React from "react";
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
import LoginLayout from "./components/LoginLayout";
import { Slider } from "./components/Slider";
import { Stack, Ratio } from "react-bootstrap";

function NozamaApp() {
  return (
    <div className="container">
      <Header logo={logo} appName={config.app.name} />
      <main className="container" style={{ marginTop: "110px" }}>
        <div className="row" style={{ justifyContent: "center" }}>
          {/* <LoginLayout /> */}
          <Stack gap={4}>
            <Slider />
            <Slider />
            <Slider />
            <Slider />
          </Stack>
        </div>
      </main>
      <SecureFooter copyrightYear={config.app.copyright_year} />
    </div>
  );
}

export default NozamaApp;
