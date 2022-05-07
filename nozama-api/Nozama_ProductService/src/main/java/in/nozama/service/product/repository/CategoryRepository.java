package in.nozama.service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nozama.service.product.model.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity	, Long> {

}
