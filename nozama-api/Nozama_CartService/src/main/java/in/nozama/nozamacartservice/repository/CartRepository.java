package in.nozama.nozamacartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nozama.nozamacartservice.model.Item;

@Repository
public interface CartRepository extends JpaRepository<Item, Long> {

	/*@Modifying
	@Query("delete Cart cart where cart.userid = :userid and cart.productCode = :productCode and cart.status =:status")
	List<Cart> delete(@Param("userid") Long userId, @Param("productCode") Long productCode, @Param("status") String status);*/

	List<Item> deleteItemByItemId(Long itemId);
	List<Item> findByItemId(Long itemId);

}
