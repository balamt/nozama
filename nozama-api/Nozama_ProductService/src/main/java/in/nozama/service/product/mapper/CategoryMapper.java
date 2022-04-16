package in.nozama.service.product.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.nozama.service.product.model.AddCategoryRequest;
import in.nozama.service.product.model.AddCategoryResponse;
import in.nozama.service.product.model.CategoryEntity;
import in.nozama.service.product.model.CategoryResponse;

@Component
public class CategoryMapper {

	public CategoryEntity map(AddCategoryRequest from, CategoryEntity to) {
		if (from != null && to != null) {
			to.setCategory(from.getCategory());
			to.setCategoryCode(from.getCategoryCode());
		}
		return to;
	}

	public AddCategoryResponse map(CategoryEntity from, AddCategoryResponse to) {
		if (from != null && to != null) {
			to.setCategory(from.getCategory());
			to.setCategoryCode(from.getCategoryCode());
			to.setCategoryId(from.getCategoryId());
			to.setStatus("SUCCESS");
		}
		return to;
	}

	public CategoryResponse map(CategoryEntity from, CategoryResponse to) {
		if (from != null && to != null) {
			List<CategoryEntity> categories = new ArrayList();
			categories.add(from);
			to.setCategories(categories);
			to.setSize(categories.size());
		}
		return to;
	}
}
