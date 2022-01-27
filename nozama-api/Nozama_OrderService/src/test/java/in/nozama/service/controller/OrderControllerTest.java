package in.nozama.service.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nozama.service.entity.Order;
import in.nozama.service.entity.Status;
import in.nozama.service.exception.OrderNotFoundException;
import in.nozama.service.repository.OrderRepository;
import in.nozama.service.service.OrderService;
import in.nozama.service.util.OrderTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
public class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private RestTemplate restTemplate;
    private Long orderId = 1L;
    private Long orderId2 = 2L;
    private Long orderId3 = 3L;

    private List<Optional<Order>> orders;
    private Order order1, order2, order3;

    @Before
    public void setUp(){

        order1 = OrderTestUtil.createOrderModel(orderId, Status.NEW, null, 120d,120d,
                0.0,Boolean.FALSE);

        order2 = OrderTestUtil.createOrderModel(2L, Status.SHIP_CREATED, null, 200d,180d,
                10.0,Boolean.TRUE);

        order3 = OrderTestUtil.createOrderModel(orderId3, Status.ORDER_CANCELLED, null, 200d,180d,
                10.0,Boolean.TRUE);

        orders = new ArrayList<>();
        orders.add(Optional.of(order1));
        orders.add(Optional.of(order2));
        orders.add(Optional.of(order3));

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

    @Test
    public void testCreateNewOrder() throws Exception {
        when(orderService.createNewOrder(order1)).thenReturn(order1);

        RequestBuilder builder = MockMvcRequestBuilders.post("/order/new")
                .content(asJsonString(order1))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(200, response.getResponse().getStatus());
        verify(orderService, times(1)).createNewOrder(order1);

    }

    @Test
    public void testUpdateOrder() throws Exception {
        when(orderService.updateOrder(order1)).thenReturn(order1);

        RequestBuilder builder = MockMvcRequestBuilders.put("/order/update")
                .content(asJsonString(order1))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(200, response.getResponse().getStatus());
        verify(orderService, times(1)).updateOrder(order1);

    }

    @Test
    public void testCanceleOrder() throws Exception {
        when(orderService.getOrderById(orderId3)).thenReturn(order3);
        when(orderService.updateOrderStatus(orderId3, Status.ORDER_CANCELLED)).thenReturn(order3);

        RequestBuilder builder = MockMvcRequestBuilders.post(String.format("/order/cancel/%d", orderId3))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(200, response.getResponse().getStatus());
        verify(orderService, times(1)).getOrderById(orderId3);
        verify(orderService, times(1)).updateOrderStatus(orderId3, Status.ORDER_CANCELLED);

    }

    @Test
    public void testProcessOrder() throws Exception {
        order2.setStatus(Status.NEW);
        when(orderService.getOrderById(orderId2)).thenReturn(order2);
        when(orderService.updateOrderStatus(orderId2, Status.SHIP_CREATED)).thenReturn(order2);

        RequestBuilder builder = MockMvcRequestBuilders.post(String.format("/order/process/%d", orderId2))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(200, response.getResponse().getStatus());
        verify(orderService, times(1)).getOrderById(orderId2);
        verify(orderService, times(1)).updateOrderStatus(orderId2, Status.SHIP_CREATED);

    }


    @Test
    public void testProcessOrderReturnNotModified() throws Exception {
        order2.setStatus(Status.SHIP_CREATED);
        when(orderService.getOrderById(orderId2)).thenReturn(order2);

        RequestBuilder builder = MockMvcRequestBuilders.post(String.format("/order/process/%d", orderId2))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(304, response.getResponse().getStatus());
        verify(orderService, times(1)).getOrderById(orderId2);

    }


    @Test
    public void testGetActiveOrdersHavingShipOnlyStatus() throws Exception {

        when(orderService.getOrdersWithShipStatus()).thenReturn(orders);

        RequestBuilder builder = MockMvcRequestBuilders.get(String.format("/order/ship"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult response = mockMvc.perform(builder).andReturn();

        assertEquals(200, response.getResponse().getStatus());
        verify(orderService, times(1)).getOrdersWithShipStatus();

    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
