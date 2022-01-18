package in.nozama.service.service;

import in.nozama.service.entity.Order;
import in.nozama.service.entity.Status;
import in.nozama.service.exception.OrderNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {


    Order getOrderById(Long orderId) throws OrderNotFoundException;

    Order createNewOrder(Order order);

    Order updateOrder(Order order);

    Order updateOrderStatus(Long orderId, Status status) throws OrderNotFoundException;

    List<Optional<Order>> getOrdersWithShipStatus();
}
