import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { FloatingLabel, Form } from "react-bootstrap";
import "react-bootstrap-typeahead/css/Typeahead.css";

import { Typeahead } from "react-bootstrap-typeahead";
import { fetchAllCategory, getAllCategory } from "../../slices/CategorySlice";

import { defaultCategory } from "./cat_json.jsx";

function Category(props) {
  const dispatch = useDispatch();

  const [selectedCategory, setSelectedCategory] = useState();

// User cannot add new category of their choice, So we limit the category based on the pre fixed category, listed in the cat_json.jsx file    
//   useEffect(() => {
//     const getCategories = async () => {
//       dispatch(await fetchAllCategory());
//     };
//     getCategories();
//   }, [dispatch]);

//   const categoryOptions = useSelector(getAllCategory);

    const selectionChangedHandler = (val) => { 
        setSelectedCategory(val);
        props.getSelectedCategory(selectedCategory);
    };

  return (
    <Typeahead
    //   allowNew
      className="mb-3 form-floating bg-white text-gray-400"
      id="product-category"
      labelKey="category"
      placeholder="Category"
      options={defaultCategory}
      onChange={selectionChangedHandler}
      selected={selectedCategory}
    />
  );
}

export default Category;
