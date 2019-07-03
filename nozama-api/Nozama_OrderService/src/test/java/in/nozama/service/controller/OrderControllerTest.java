package in.nozama.service.controller;

import in.nozama.service.exception.OrderNotFoundException;
import in.nozama.service.model.Order;
import in.nozama.service.model.Status;
import in.nozama.service.repository.OrderRepository;
import in.nozama.service.service.OrderService;
import in.nozama.service.util.OrderTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class, secure = false)
public class OrderControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private RestTemplate restTemplate;
    private Long orderId = 1L;

    private List<Order> orders;
    private Order order1, order2;

    @Before
    public void setUp(){

        order1 = OrderTestUtil.createOrderModel(orderId, Status.NEW, null, 120d,120d,
                0.0,Boolean.FALSE);

        order2 = OrderTestUtil.createOrderModel(2L, Status.SHIP_CREATED, null, 200d,180d,
                10.0,Boolean.TRUE);

    }

    @Test
    public void testTestServerStatus() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/order");

        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals("Order Server is Up and Running...", response.getResponse().getContentAsString());

    }

    @Test
    public void testGetOrderById() throws Exception {
        when(orderService.getOrderById(orderId)).thenReturn(order1);

        RequestBuilder builder = MockMvcRequestBuilders.get(String.format("/order/%d",orderId));
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(OrderTestUtil.GET_ORDER_BY_ID_RESPONSE, response.getResponse().getContentAsString());
        verify(orderService,times(1)).getOrderById(orderId);
    }

    @Test
    public void testGetOrderByIdOrderNotFoundException() throws Exception {
        when(orderService.getOrderById(anyLong())).thenThrow(OrderNotFoundException.class);

        RequestBuilder builder = MockMvcRequestBuilders.get(String.format("/order/%d",3));
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(404, response.getResponse().getStatus());
        verify(orderService,times(1)).getOrderById(3L);
    }


}
