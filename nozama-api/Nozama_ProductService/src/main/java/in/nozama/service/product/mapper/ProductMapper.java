package in.nozama.service.product.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import in.nozama.service.product.model.AddProductRequest;
import in.nozama.service.product.model.AddProductResponse;
import in.nozama.service.product.model.Product;
import in.nozama.service.product.model.ProductResponse;

@Component
public class ProductMapper {

	public void map(AddProductRequest from, Product to) {
		if (from != null && to != null) {
			to.setProductName(from.getProductName());
			to.setProductCode(from.getProductCode());
			to.setProductImg(from.getProductImg());
			to.setCategory(from.getCategory());
			to.setPricePerItem(from.getPricePerItem());
			to.setStockQuantity(from.getStockQuantity());
			to.setWarehouse(from.getWarehouse());
			to.setTags(from.getTags());
			to.setSeller(from.getSeller());
			to.setRating(from.getRating());
			to.setProductDescription(from.getProductDescription());
		}
	}

	public void map(Product from, AddProductResponse to) {

		if (from != null && to != null) {
			to.setProductId(from.getProductId());
			to.setProductCode(from.getProductCode());
			to.setProductName(from.getProductName());
			to.setProductImg(from.getProductImg());
			to.setCategory(from.getCategory());
			to.setPricePerItem(from.getPricePerItem());
			to.setStockQuantity(from.getStockQuantity());
			to.setWarehouse(from.getWarehouse());
			to.setTags(from.getTags());
			to.setSeller(from.getSeller());
			to.setRating(from.getRating());
			to.setProductDescription(from.getProductDescription());
		}
	}

	public ProductResponse map(Product from) {
		ProductResponse to = new ProductResponse();
		if (from != null) {
			to.setProductId(from.getProductId());
			to.setProductCode(from.getProductCode());
			to.setProductName(from.getProductName());
			to.setProductImg(from.getProductImg());
			to.setCategory(from.getCategory());
			to.setPricePerItem(from.getPricePerItem());
			to.setStockQuantity(from.getStockQuantity());
			to.setWarehouse(from.getWarehouse());
			to.setTags(from.getTags());
			to.setSeller(from.getSeller());
			to.setRating(from.getRating());
			to.setProductDescription(from.getProductDescription());
		}
		return to;
	}

	public Page<ProductResponse> map(Page<Product> from) {
		return from.map(this::map);
	}

	public Page<ProductResponse> map(List<Product> from) {
		
		AddProductResponse addProductResponse = null;
		List<AddProductResponse> responses = new ArrayList<>();

		for (Product p : from) {
			addProductResponse = new AddProductResponse();
			this.map(p, addProductResponse);
			responses.add(addProductResponse);
		}
		return new PageImpl(responses);
	}

}
