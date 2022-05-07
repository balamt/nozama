import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Signin from "./components/Auth/Signin";
import Signout from "./components/Auth/Signout";

import logo from "./logo.svg";
import "./NozamaAdminApp.css";
import Home from "./components/Home/Home";
import Product from "./components/Product/Product";
import { Container } from "react-bootstrap";

function NozamaAdminApp() {
  return (
    <Router>
      <Container fluid>
        <Header />
        <div className="app-body">
          <main style={{ marginTop: "1px" }}>
            <div className="row" style={{ justifyContent: "center" }}>
              <Routes>
                <Route
                  path="404"
                  element={
                    <div className="flex items-center justify-center">
                      <h2 className="text-2xl font-extrabold text-red-700">
                        PAGE NOT FOUND
                      </h2>
                    </div>
                  }
                />
                <Route path="*" element={<Navigate replace to="/404" />} />
                <Route path="/" element={<Home />} />
                <Route path="/products" element={<Product />} />
                <Route path="/settings" element={<h2></h2>} />
                <Route path="/users" element={<h2></h2>} />
                <Route path="/dashboard" element={<h2></h2>} />
                <Route path="/signin" element={<Signin />} />
                <Route path="/signout" element={<Signout />} />
              </Routes>
            </div>
          </main>
        </div>
        <Footer />
      </Container>
    </Router>
  );
}

export default NozamaAdminApp;
