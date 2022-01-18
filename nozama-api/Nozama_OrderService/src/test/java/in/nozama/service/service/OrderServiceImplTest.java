package in.nozama.service.service;

import in.nozama.service.entity.Order;
import in.nozama.service.entity.Status;
import in.nozama.service.exception.OrderNotFoundException;
import in.nozama.service.repository.OrderRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    private Long orderId = 1L;
    private List<Optional<Order>> orders;
    private Long user = 2L;

    private Order order1;
    private Order order2;

    @Before
    public void setUp() {

        orders = new ArrayList<>();

        order1 = new Order();
        order1.setOrderid(orderId);
        order1.setDiscountApplied(Boolean.FALSE);
        order1.setDiscountPercentage(0.0);
        order1.setFinalprice(120d);
        order1.setTotalPrice(120d);
        order1.setUser(user);
        order1.setStatus(Status.SHIP_CREATED);

        order2 = new Order();
        order2.setOrderid(2L);
        order2.setDiscountApplied(Boolean.FALSE);
        order2.setDiscountPercentage(0.0);
        order2.setFinalprice(120d);
        order2.setTotalPrice(120d);
        order2.setUser(user);

        orders.add(Optional.ofNullable(order1));
        orders.add(Optional.ofNullable(order2));

    }
    @Test
    public void testGetOrderById() throws OrderNotFoundException {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order1));
        Order result = orderService.getOrderById(orderId);
        assertEquals(orderId, result.getOrderid());
        Mockito.verify(orderRepository,Mockito.times(1)).findById(anyLong());
    }

    @Test(expected = OrderNotFoundException.class)
    public void testGetOrderByIdOrderNotFoundException() throws OrderNotFoundException {
        Order result = orderService.getOrderById(2L);
        Mockito.verify(orderRepository,Mockito.times(1)).findById(anyLong());
    }

    @Test
    public void testCreateNewOrder(){
        when(orderRepository.saveAndFlush(order1)).thenReturn(order1);
        Order result = orderService.createNewOrder(order1);
        verify(orderRepository, times(1)).saveAndFlush(order1);

    }

    @Test
    public void testUpdateOrder(){
        when(orderService.createNewOrder(order1)).thenReturn(order1);
        Order result = orderService.updateOrder(order1);
        assertEquals(orderId, result.getOrderid());

    }

    @Test
    public void testUpdateOrderStatus() throws OrderNotFoundException {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order1));
        when(orderRepository.updateOrderStatus(Status.NEW, orderId)).thenReturn(1);

        Order result = orderService.updateOrderStatus(orderId, Status.NEW);

        verify(orderRepository, times(1)).updateOrderStatus(Status.NEW, orderId);
        assertEquals(orderId, result.getOrderid());
    }

    @Test
    public void testGetOrdersWithShipStatus() {
        when(orderRepository.findByStatusInOrderByOrderid(anyCollection())).thenReturn(orders);

        List<Optional<Order>> result = orderService.getOrdersWithShipStatus();

        verify(orderRepository, times(1)).findByStatusInOrderByOrderid(anyCollection());


    }

}
