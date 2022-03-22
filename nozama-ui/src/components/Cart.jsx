import React, { useState } from "react";

const Cart = (props) => {
  // useState is used for updating the UI value
  const [count, setCount] = useState(0);

  let IncrementCount = () => {
    setCount(count + 2);
  };

  return (
    <button onClick={IncrementCount}>Counter Incremented to {count}</button>
  );
};

export default Cart;
