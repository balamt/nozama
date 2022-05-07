package in.nozama.service.product.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.nozama.service.dto.product.AddCategoryRequest;
import in.nozama.service.dto.product.AddCategoryResponse;
import in.nozama.service.dto.product.CategoryResponse;
import in.nozama.service.product.model.CategoryEntity;

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
			List<String> categories = new ArrayList();
			categories.add(from.getCategory());
			to.setCategories(categories);
			to.setSize(categories.size());
		}
		return to;
	}

	public CategoryResponse map(List<CategoryEntity> from, CategoryResponse to) {
		if (to == null) {
			to = new CategoryResponse();
		}

		if (from != null) {
			List<String> cate = new ArrayList<>();
			for (final CategoryEntity c : from) {
				cate.add(c.getCategory());
			}
			to.setSize(from.size());
			to.setCategories(cate);
		}

		return to;
	}
}
