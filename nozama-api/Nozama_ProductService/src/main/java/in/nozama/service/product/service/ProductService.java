package in.nozama.service.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.nozama.service.product.exception.ProductExistsException;
import in.nozama.service.product.exception.ProductNotFoundException;
import in.nozama.service.product.model.AddProductRequest;
import in.nozama.service.product.model.AddProductResponse;
import in.nozama.service.product.model.Category;
import in.nozama.service.product.model.Product;
@Service
public interface ProductService {

	List<Product> getAllProducts();

    Product getProductById(Long productId) throws ProductNotFoundException;

    List<Category> getAllCategories();

    List<Product> getProductsByCategory(String categoryId);

	AddProductResponse checkAndAddNewProduct(AddProductRequest productRequest) throws ProductExistsException;

	boolean updateProductImage(Long productId, String fileName);

}
