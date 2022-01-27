package in.nozama.service.product.controller;

import in.nozama.service.product.exception.ProductNotFoundException;
import in.nozama.service.product.model.Product;
import in.nozama.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
/*@CrossOrigin(origins = {"http://localhost:4200", "http://balahp:4200","http://localhost:4200/"})*/
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();				
		return ResponseEntity.ok().body(products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "productId") Long productId)
			throws ProductNotFoundException {
		return ResponseEntity.ok(productService.getProductById(productId));
	}

	@GetMapping("/by/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable(name = "categoryId") String categoryId)
			throws ProductNotFoundException {
		return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
	}

	@ExceptionHandler
	void noProductFoundExceptionHandler(ProductNotFoundException pnfe, HttpServletResponse response)
			throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), pnfe.getMessage());
	}

}
