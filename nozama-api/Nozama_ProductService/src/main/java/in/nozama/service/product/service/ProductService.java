package in.nozama.service.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import in.nozama.service.dto.product.AddProductRequest;
import in.nozama.service.dto.product.AddProductResponse;
import in.nozama.service.dto.product.ProductByCategoryResponse;
import in.nozama.service.dto.product.ProductResponse;
import in.nozama.service.product.exception.ProductExistsException;
import in.nozama.service.product.exception.ProductNotFoundException;
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

	Page<ProductResponse> findByCategoryWithPage(Long page, int defaultItemCount);

	Page<ProductResponse> findByCategoryWithPage(String categoryId, Long page, int defaultItemCount);

	List<ProductByCategoryResponse> getAllProductsByCategory();

}
