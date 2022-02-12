package in.nozama.service.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nozama.service.product.model.Category;
import in.nozama.service.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="SELECT DISTINCT P.CATEGORY FROM Products P", nativeQuery = true)
     List<Category> findCategories();

	Product findByProductName(String productName);
}
