package in.nozama.service.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.service.product.model.Category;
import in.nozama.service.product.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/category")
/**
 *  This category controller is of now use, but we can change the productservice logic to make use of this controller.
 * @author balam
 *
 */
public class CategoryController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = productService.getAllCategories().stream().sorted().collect(Collectors.toList());
        return ResponseEntity.ok().body(categories);
    }

}
