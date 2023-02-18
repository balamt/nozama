package in.nozama.service.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import in.nozama.service.dto.product.AddProductRequest;
import in.nozama.service.dto.product.AddProductResponse;
import in.nozama.service.dto.product.ProductByCategoryResponse;
import in.nozama.service.dto.product.ProductResponse;
import in.nozama.service.product.exception.ProductExistsException;
import in.nozama.service.product.exception.ProductNotFoundException;
import in.nozama.service.product.mapper.ProductMapper;
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
	public List<ProductResponse> getAllProducts() {
		return productMapper.mapToResponse(productRepository.findAll());
	}

	@Override
	public ProductResponse getProductById(Long productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (!optionalProduct.isPresent()) {
			throw new ProductNotFoundException("No Such Product Found");
		}

		return productMapper.map(optionalProduct.get());
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

	@Override
	public Page<ProductResponse> findByCategoryWithPage(Long page, int defaultItemCount) {
		return this.findByCategoryWithPage(null, page, defaultItemCount);
	}

	@Override
	public Page<ProductResponse> findByCategoryWithPage(String categoryId, Long page, int defaultItemCount) {
		if (page <= 0) {
			page = 1L;
		}
		Pageable pageable = PageRequest.of((int) (page - 1), defaultItemCount)
				.withSort(Sort.by(Direction.DESC, "createdOn"));
		Page<ProductResponse> pageProductResponsePage = productMapper.map(productRepository.findAll(pageable));
		if (categoryId != null && (!categoryId.isEmpty())) {
			return productMapper
					.map(productRepository.findByCategory(Category.valueOf(categoryId.toUpperCase()), pageable));
		}
		return pageProductResponsePage;
	}

	@Override
	public List<ProductByCategoryResponse> getAllProductsByCategory() {
		List<Category> categories = getAllCategories();
		List<ProductByCategoryResponse> response = new ArrayList<>();
		
		for(Category c : categories) {
			ProductByCategoryResponse r = new ProductByCategoryResponse();
			r.setCategory(c.name());
			r.setProducts(productMapper.mapToResponse(productRepository.findByCategory(c)));
			response.add(r);
		}

		
		return response;
	}

	@Override
	public ProductResponse deleteProductById(Long productId) {
		ProductResponse response = new ProductResponse();
		response = productMapper.map(productRepository.findById(productId), response);
		
		return response;
	}

	@Override
	public List<ProductResponse> fetchProductByStockLimit(Long limit) {
		return productMapper.mapToResponse(productRepository.findProductsStockBelowLimit(limit));
	}

}
