package in.nozama.service.product.service;

import in.nozama.service.dto.product.AddCategoryRequest;
import in.nozama.service.dto.product.AddCategoryResponse;
import in.nozama.service.dto.product.CategoryRequest;
import in.nozama.service.dto.product.CategoryResponse;
import in.nozama.service.product.exception.CategoryExistException;
import in.nozama.service.product.exception.CategoryNotFoundException;

public interface CategoryService {
	
	CategoryResponse getAllCategory() throws CategoryNotFoundException;
	
	CategoryResponse getCategory(CategoryRequest categoryRequest) throws CategoryNotFoundException;
	
	AddCategoryResponse addNewCategoryIfNotExist(AddCategoryRequest addCategoryRequest) throws CategoryExistException;
	
}
