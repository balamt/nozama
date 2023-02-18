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

import "./NozamaAdminApp.css";
import Home from "./components/Home/Home";
import Product from "./components/Product/Product";
import { Container } from "react-bootstrap";
import {
  ROUTE_DASHBOARD,
  ROUTE_EDIT_PRODUCT,
  ROUTE_HOME,
  ROUTE_ORDER,
  ROUTE_PRODUCT,
  ROUTE_SETTINGS,
  ROUTE_SIGNIN,
  ROUTE_SIGNUP,
  ROUTE_ADD_USER,
  ROUTE_EDIT_USER,
  ROUTE_FIND_USER,
} from "./AdminUIConst";
import EditProduct from "./components/Product/EditProduct";
import AddUser from "./components/User/AddUser";
import Users from "./components/User/Users";

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
                <Route path={ROUTE_HOME} element={<Home />} />
                <Route exact path={ROUTE_PRODUCT} element={<Product />} />
                <Route
                  exact
                  path={ROUTE_EDIT_PRODUCT}
                  element={<EditProduct />}
                />
                <Route path={ROUTE_ORDER} element={<h2>ORDER</h2>} />
                <Route path={ROUTE_SETTINGS} element={<h2>SETTINGS</h2>} />
                <Route exact path={ROUTE_ADD_USER} element={<AddUser />} />
                <Route exact path={ROUTE_FIND_USER} element={<Users />} />
                <Route
                  exact
                  path={ROUTE_EDIT_USER}
                  element={<h2>Edit USER</h2>}
                />
                <Route path={ROUTE_DASHBOARD} element={<h2>DASHBOARD</h2>} />
                <Route path={ROUTE_SIGNUP} element={<Signin />} />
                <Route path={ROUTE_SIGNIN} element={<Signout />} />
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
