package in.nozama.service.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.nozama.service.product.model.Category;
import in.nozama.service.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="SELECT DISTINCT(category) FROM `products`", nativeQuery = true)
     List<Category> findCategories();
    
    Page<Product> findByCategory(Category category, Pageable pageable);
    
    List<Product> findByCategory(Category category);
    
	Product findByProductName(String productName);
	
	@Query(value="SELECT * FROM `products` WHERE stock_quantity <= :limit", nativeQuery = true)
	List<Product> findProductsStockBelowLimit(@Param("limit") Long limit);
}
