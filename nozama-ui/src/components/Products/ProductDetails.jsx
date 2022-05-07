import React, { useState, useEffect } from "react";

import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import {
  fetchAsyncProductById,
  fetchAsyncProductImageById,
  getSelectedProduct,
  getSelectedProductImage,
} from "../../features/product/productSlice";

const ProductDetails = () => {
  const { productId } = useParams();

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchAsyncProductById(productId));
    dispatch(fetchAsyncProductImageById(productId));
  }, [dispatch, productId]);

  const product = useSelector(getSelectedProduct);
  const productImg = useSelector(getSelectedProductImage);
  console.log(productImg);
  return (
    <div>
      <h3>ProductDetails</h3>
      <div>
        <div>
          <h4>{product.productName}</h4>
        </div>
        <div>
          <div>
            <img src={productImg} alt="" />
          </div>
          <div>{product.productDescription}</div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
