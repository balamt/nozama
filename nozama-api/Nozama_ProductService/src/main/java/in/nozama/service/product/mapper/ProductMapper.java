package in.nozama.service.product.mapper;

import org.springframework.stereotype.Component;

import in.nozama.service.product.model.AddProductRequest;
import in.nozama.service.product.model.AddProductResponse;
import in.nozama.service.product.model.Product;

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
		}

	}

}
