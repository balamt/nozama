package in.nozama.service.product.service;

import in.nozama.service.exception.ProductNotFoundException;
import in.nozama.service.model.Category;
import in.nozama.service.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {

	List<Product> getAllProducts();

    Product getProductById(Long productId) throws ProductNotFoundException;

    List<Category> getAllCategories();

    List<Product> getProductsByCategory(String categoryId);
}
