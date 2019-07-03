package in.nozama.service.product.service;

import in.nozama.service.exception.ProductNotFoundException;
import in.nozama.service.model.Product;
import in.nozama.service.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}
