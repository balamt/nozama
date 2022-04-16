package in.nozama.service.product.service;

import in.nozama.service.product.exception.CategoryExistException;
import in.nozama.service.product.exception.CategoryNotFoundException;
import in.nozama.service.product.model.AddCategoryRequest;
import in.nozama.service.product.model.AddCategoryResponse;
import in.nozama.service.product.model.CategoryRequest;
import in.nozama.service.product.model.CategoryResponse;

public interface CategoryService {
	
	CategoryResponse getAllCategory() throws CategoryNotFoundException;
	
	CategoryResponse getCategory(CategoryRequest categoryRequest) throws CategoryNotFoundException;
	
	AddCategoryResponse addNewCategoryIfNotExist(AddCategoryRequest addCategoryRequest) throws CategoryExistException;
	
}
