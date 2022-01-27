package in.nozama.service.controller;

import in.nozama.service.entity.Order;
import in.nozama.service.entity.Status;
import in.nozama.service.exception.OrderNotFoundException;
import in.nozama.service.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    /*private CountDownLatch latch = new CountDownLatch(1);*/

    @Autowired
    OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<String> testServerStatus(){
        return ResponseEntity.ok("Order Server is Up and Running...");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name = "orderId") Long orderId)
            throws OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/new")
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order){
        Order newOrder = orderService.createNewOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        Order updateOrder = orderService.updateOrder(order);
        return ResponseEntity.ok().body(updateOrder);
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Order> cancelOrder(@PathVariable(name = "orderId") Long orderId) throws OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);
        Order updateOrder = orderService.updateOrderStatus(order.getOrderid(), Status.ORDER_CANCELLED);
        if(LOG.isDebugEnabled()){
            LOG.debug(String.format( "Updated Order %s ",updateOrder.toString()));
        }
        updateOrder.setStatus(Status.ORDER_CANCELLED);
        return ResponseEntity.ok().body(updateOrder);
    }

    @PostMapping("/process/{orderId}")
    public ResponseEntity<Order> processOrder(@PathVariable(name = "orderId") Long orderId) throws OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);
        if(order.getStatus().equals(Status.SHIP_CREATED)){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        orderService.updateOrderStatus(order.getOrderid(), Status.SHIP_CREATED);
        /**
         * Call Shipment Service via RestTemplate to Process the Shipment
         */
        order.setStatus(Status.SHIP_CREATED);
        order.setOrderid(orderId);
        return ResponseEntity.ok(order);
    }

    /**
     * This Method is used for the Shipment Batch, to get all the available orders which has
     * status of SHIP_* so that the batch can move the status forward.
     * @return List of Orders
     */
    @GetMapping("/ship")
    public ResponseEntity<List<Optional<Order>>> getActiveOrdersHavingShipOnlyStatus(){
        List<Optional<Order>> orders = orderService.getOrdersWithShipStatus();
        return ResponseEntity.ok(orders);
    }
/*
    public CountDownLatch getLatch(){
        return latch;
    }

    @KafkaListener(topics = {"${kafka.topic}"})
    public void updateOrderFromKafka(Order order){
        LOG.info("Received Order Update for Order Id " + order.getOrderid());
        orderService.updateOrder(order);
        latch.countDown();
    }*/

    @ExceptionHandler
    void oderNotFound(OrderNotFoundException onfe, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}
