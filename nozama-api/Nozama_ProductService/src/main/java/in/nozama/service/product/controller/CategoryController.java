package in.nozama.service.product.controller;

import in.nozama.service.product.model.Category;
import in.nozama.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/category")
/*@CrossOrigin(origins = {"http://localhost:4200", "http://balahp:4200","http://localhost:4200/"})*/
public class CategoryController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = productService.getAllCategories().stream().sorted().collect(Collectors.toList());
        return ResponseEntity.ok().body(categories);
    }

}
