package in.nozama.kafka.order.updater.controller;

import in.nozama.kafka.order.updater.service.OrderInfoUpdaterService;
import in.nozama.service.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-info/update")
public class OrderInfoUpdaterController {

    private final static Logger LOG = LoggerFactory.getLogger(OrderInfoUpdaterController.class);

    @Autowired
    OrderInfoUpdaterService orderInfoUpdaterService;

    @PutMapping
    public ResponseEntity updateOrder(@RequestBody Order order){
        orderInfoUpdaterService.sendOrderInfo(order);

        LOG.info("Order Info Update for Order " + order.getOrderid());

        return ResponseEntity.ok("Order Info Update for Order " + order.getOrderid());
    }

    @GetMapping("/test")
    public ResponseEntity testService(){
        return ResponseEntity.ok("Order Info Update Service Running");
    }

}
