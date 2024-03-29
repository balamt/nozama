package in.nozama.service.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.nozama.service.dto.product.AddProductRequest;
import in.nozama.service.dto.product.AddProductResponse;
import in.nozama.service.dto.product.ProductImageUploadResponse;
import in.nozama.service.dto.product.ProductResponse;
import in.nozama.service.product.exception.ProductExistsException;
import in.nozama.service.product.exception.ProductImageUploadException;
import in.nozama.service.product.exception.ProductNotFoundException;
import in.nozama.service.product.service.ProductImageUploadService;
import in.nozama.service.product.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductImageUploadService productImageUploadService;

	@GetMapping("/all")
	public ResponseEntity getAllProducts() {
		return ResponseEntity.ok().body(productService.getAllProducts());
	}

	@GetMapping("/all/by/category")
	public ResponseEntity getAllProductsByCategory() {
		return ResponseEntity.ok(productService.getAllProductsByCategory());
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable(name = "productId") Long productId)
			throws ProductNotFoundException {
		return ResponseEntity.ok(productService.getProductById(productId));
	}

	@GetMapping(path = { "/by/category", "/by/category/{categoryId}" })
	public ResponseEntity<Object> getProductByCategory(
			@PathVariable(name = "categoryId", required = false) String categoryId,
			@RequestParam(name = "page", required = false, defaultValue = "1") Long page)
			throws ProductNotFoundException {
		int defaultItemCount = 6;
		Page<ProductResponse> responseBody = productService.findByCategoryWithPage(categoryId, page, defaultItemCount);
		if (page >= 1 && categoryId == null) {
			responseBody = productService.findByCategoryWithPage(page, defaultItemCount);
			return ResponseEntity.ok(responseBody);
		} else if (page <= 0 && categoryId != null) {
			return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
		}
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping("/add")
	public ResponseEntity<AddProductResponse> addNewProduct(@RequestBody AddProductRequest productRequest)
			throws ProductExistsException {
		return ResponseEntity.ok(productService.checkAndAddNewProduct(productRequest));
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<AddProductResponse> deleteProductByProductId(
			@PathVariable(value = "productId") Long productId) throws ProductImageUploadException {
		ProductResponse response = productService.deleteProductById(productId);
		if (response != null && response.getProductImg() != null) {
			productImageUploadService.deleteProductImage(response.getProductImg());
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/upload")
	public ResponseEntity<ProductImageUploadResponse> uploadProductImage(@RequestParam("productimg") MultipartFile file,
			@RequestParam("productid") Long productId) throws ProductImageUploadException {
		String fileName = productImageUploadService.saveProductImage(file, productId);
		ProductImageUploadResponse response = new ProductImageUploadResponse();
		if (productService.updateProductImage(productId, fileName)) {
			response.setProductId(productId);
		} else {
			response.setError(true);
			response.setStatus("ERROR Occured");
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/image/{productid}")
	public ResponseEntity<byte[]> viewProductImage(@PathVariable(name = "productid") Long productId)
			throws ProductImageUploadException, ProductNotFoundException, IOException {
		// Get the Product details by Id
		ProductResponse prod = productService.getProductById(productId);
		// Get the Product Image and read it as InputStream
		InputStream is = productImageUploadService.loadProductImage(prod.getProductImg()).getInputStream();
		// Get the file content type
		String mime = URLConnection.guessContentTypeFromStream(is);
		final HttpHeaders httpHeaders = new HttpHeaders();
		// Set the head to the mime type.
		httpHeaders.setContentType(MediaType.parseMediaType(mime));
		// Return the body as byte[] and the header with content type
		return new ResponseEntity<byte[]>(is.readAllBytes(), httpHeaders, HttpStatus.OK);

	}
	
	@GetMapping("/all/by/stock/{limit}")
	public ResponseEntity fetchAllProductWithStockLimit(@PathVariable(name ="limit") Long limit) {
		return ResponseEntity.ok(productService.fetchProductByStockLimit(limit));
	}

	@ExceptionHandler
	void noProductFoundExceptionHandler(ProductNotFoundException pnfe, HttpServletResponse response)
			throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), pnfe.getMessage());
	}

	@ExceptionHandler
	void productExistsExceptionHandler(ProductExistsException pee, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_MODIFIED.value(), pee.getMessage());
	}

	@ExceptionHandler
	void productImageUploadExceptionHandler(ProductImageUploadException piue, HttpServletResponse response)
			throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), piue.getMessage());
	}

}
