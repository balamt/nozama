import React, { useState } from "react";
import { Button } from "react-bootstrap";
import { FaPlus, FaMinus } from "react-icons/fa";

function QuantityComponent(props) {
  const [quantity, setQuantity] = useState(1);

  const increaseQuantity = (event) => {
    event.preventDefault();
    let x = quantity + 1;
    if (x < props.max+1) {
      setQuantity(x);
    }
    props.getQuantity(quantity);
  };

  const decreaseQuantity = (event) => {
    event.preventDefault();
    if (quantity >= 2) {
      let x = quantity - 1;
      setQuantity(x);
    }
    props.getQuantity(quantity);
  };

  const quantityChangeHandler = (e) => {
    setQuantity(parseInt(e.target.value));
    props.getQuantity(quantity + 1);
  };
  const quantityRangeComponent = (
    <div className="d-flex justify-content-center">
      <Button
        onClick={decreaseQuantity}
      >
        <FaMinus />
      </Button>

      <input
        readOnly
        type="text"
        title={"Maximum Quantity " + (props.max ? props.max : 999)}
        max={props.max ? props.max : 999}
        style={{ border: "0px", cursor: "pointer" }}
        className="m-1 form-control form-control-lg text-xl-center"
        value={quantity}
        onChange={quantityChangeHandler}
      />
      <Button
        disabled={quantity >= props.max ? true : false}
        variant={quantity >= props.max ? "light" : "primary"}
        onClick={increaseQuantity}
      >
        <FaPlus />
      </Button>
    </div>
  );

  const outOfStock = (
    <div className="d-flex justify-content-center text-danger">
      <h3>Out of Stock</h3>
    </div>
  );
  return <>{props.max >= 1 ? quantityRangeComponent : outOfStock}</>;
}

export default QuantityComponent;
