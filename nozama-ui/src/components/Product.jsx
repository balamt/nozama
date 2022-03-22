import React from "react";

import { Slider } from "./Slider";
import { Stack, Ratio } from "react-bootstrap";

const Product = (props) => {
  return (
    <Stack gap={4}>
      <Slider />
      <Slider />
      <Slider />
      <Slider />
    </Stack>
  );
};

export default Product;
