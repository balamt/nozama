import React from "react";
import { Link } from "react-router-dom";
import { faHomeLg } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const NotFound = (props) => {
  //const [username, password, setUserName, setPassword] = useState(props.username, props.password);

  return (
    <div
      className="bs-secondary"
      style={{
        display: "inline-flex",
        "justify-content": "space-evenly",
      }}
    >
      <div className="alert alert-danger" role="alert">
        <h1 className="text-danger">404 - Not Found!</h1>
        <Link className="btn btn-danger jumbotron" to="/">
          <h2>
            Go Home &nbsp; &nbsp;{" "}
            <FontAwesomeIcon
              size="lg"
              icon={faHomeLg}
              className="text-light"
              title="Home"
            />
          </h2>
        </Link>
      </div>
    </div>
  );
};

export default NotFound;
