package in.nozama.service.repository;

import in.nozama.service.model.Order;
import in.nozama.service.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Order o set o.status = :status where o.orderid = :orderId")
    Integer updateOrderStatus(Status status, Long orderId);

    /**
     * JPA Query methods
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
     * We can write method name which can run as a query. refer above link
     * @param statuses
     * @return List<Order>
     */
    List<Optional<Order>> findByStatusInOrderByOrderid(Collection<Status> statuses);

}
