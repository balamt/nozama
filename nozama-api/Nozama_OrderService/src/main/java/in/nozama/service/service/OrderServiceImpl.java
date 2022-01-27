package in.nozama.service.service;

import in.nozama.service.entity.Order;
import in.nozama.service.entity.Status;
import in.nozama.service.exception.OrderNotFoundException;
import in.nozama.service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;


    @Override
    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if(!order.isPresent()){
            throw new OrderNotFoundException("No Such Order Found");
        }
        return order.get();
    }

    @Override
    public Order createNewOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return createNewOrder(order);
    }

    @Override
    public Order updateOrderStatus(Long order, Status status) throws OrderNotFoundException {
        LOG.info(String.format("Process Order : %s | Status : %d", order, orderRepository.updateOrderStatus(status, order)));
        return getOrderById(order);
    }

    @Override
    public List<Optional<Order>> getOrdersWithShipStatus() {

        Collection<Status> statuses = new TreeSet<>();
        statuses.add(Status.SHIP_CREATED);
        statuses.add(Status.SHIP_READY);
        statuses.add(Status.SHIP_TRANSMIT);
        statuses.add(Status.SHIP_OUT_FOR_DELIVERY);
        statuses.add(Status.SHIP_RETURNED);
        statuses.add(Status.SHIP_DELIVERED);

        return orderRepository.findByStatusInOrderByOrderid(statuses);
    }
}
