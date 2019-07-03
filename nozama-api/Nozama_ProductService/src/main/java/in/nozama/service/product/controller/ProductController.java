package in.nozama.service.product.controller;

import in.nozama.service.exception.ProductNotFoundException;
import in.nozama.service.model.Product;
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
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();				
		return ResponseEntity.ok().body(products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "productId") Long productId)
			throws ProductNotFoundException {
		return ResponseEntity.ok(productService.getProductById(productId));
	}

	@ExceptionHandler
	void noProductFoundExceptionHandler(ProductNotFoundException pnfe, HttpServletResponse response)
			throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), pnfe.getMessage());
	}

}
