package in.nozama.service.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.service.product.exception.ProductNotFoundException;
import in.nozama.service.product.model.Category;
import in.nozama.service.product.model.Product;
import in.nozama.service.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(!optionalProduct.isPresent()){
			throw new ProductNotFoundException("No Such Product Found");
		}

		return optionalProduct.get();
	}

	@Override
	public List<Category> getAllCategories() {
		return productRepository.findCategories();
	}

	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		String catId = categoryId.replace('_',' ');
		return productRepository.findAll().stream().filter(product -> catId
				.equalsIgnoreCase(product.getCategory().code()))
				.collect(Collectors.toList());
	}

}
