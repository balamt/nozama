package in.nozama.service.product.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import in.nozama.service.dto.product.AddCategoryRequest;
import in.nozama.service.dto.product.AddCategoryResponse;
import in.nozama.service.dto.product.CategoryRequest;
import in.nozama.service.dto.product.CategoryResponse;
import in.nozama.service.product.exception.CategoryExistException;
import in.nozama.service.product.exception.CategoryNotFoundException;
import in.nozama.service.product.mapper.CategoryMapper;
import in.nozama.service.product.model.CategoryEntity;
import in.nozama.service.product.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public CategoryResponse getAllCategory() throws CategoryNotFoundException {
		CategoryResponse response = new CategoryResponse();
		response = categoryMapper.map(categoryRepository.findAll(), response);
		return response;
	}

	@Override
	public CategoryResponse getCategory(CategoryRequest categoryRequest) throws CategoryNotFoundException {
		CategoryResponse response = new CategoryResponse();
		Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryRequest.getCategoryId());
		if (categoryEntity.isPresent()) {
			response = categoryMapper.map(categoryEntity.get(), response);
		}
		return response;
	}

	@Override
	public AddCategoryResponse addNewCategoryIfNotExist(AddCategoryRequest addCategoryRequest)
			throws CategoryExistException {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity = categoryMapper.map(addCategoryRequest, categoryEntity);
		categoryEntity = categoryRepository.saveAndFlush(categoryEntity);
		AddCategoryResponse response = new AddCategoryResponse();
		response = categoryMapper.map(categoryEntity, response);
		return response;
	}

}
