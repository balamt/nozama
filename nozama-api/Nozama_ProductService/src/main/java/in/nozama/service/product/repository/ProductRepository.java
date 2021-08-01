package in.nozama.service.product.repository;

import in.nozama.service.model.Category;
import in.nozama.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="SELECT DISTINCT P.CATEGORY FROM Products P", nativeQuery = true)
     List<Category> findCategories();

}
