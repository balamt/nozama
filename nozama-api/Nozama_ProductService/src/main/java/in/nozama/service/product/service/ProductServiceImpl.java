package in.nozama.service.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.service.product.exception.ProductExistsException;
import in.nozama.service.product.exception.ProductNotFoundException;
import in.nozama.service.product.mapper.ProductMapper;
import in.nozama.service.product.model.AddProductRequest;
import in.nozama.service.product.model.AddProductResponse;
import in.nozama.service.product.model.Category;
import in.nozama.service.product.model.Product;
import in.nozama.service.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (!optionalProduct.isPresent()) {
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
		String catId = categoryId.replace('_', ' ');
		return productRepository.findAll().stream()
				.filter(product -> catId.equalsIgnoreCase(product.getCategory().code())).collect(Collectors.toList());
	}

	@Override
	public AddProductResponse checkAndAddNewProduct(AddProductRequest productRequest) throws ProductExistsException {
		Product prod = productRepository.findByProductName(productRequest.getProductName());
		if (prod != null && prod.getProductCode().equalsIgnoreCase(productRequest.getProductCode())) {
			throw new ProductExistsException(String.format("Error: Product %s with code %s already exists",
					productRequest.getProductName(), productRequest.getProductCode()));
		}
		prod = new Product();
		productMapper.map(productRequest, prod);
		AddProductResponse resp = new AddProductResponse();
		productMapper.map(productRepository.save(prod), resp);
		return resp;
	}

	@Override
	public boolean updateProductImage(Long productId, String fileName) {
		Product prod = productRepository.getById(productId);
		prod.setProductImg(fileName);
		prod = productRepository.saveAndFlush(prod);
		return (!prod.getProductImg().isBlank());
	}

}
