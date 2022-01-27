package in.nozama.scheduler.delivery.processor;

import in.nozama.service.entity.Order;
import in.nozama.service.entity.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemProcessor implements ItemProcessor<Order[], List<Order>>{


    private final static Logger LOG = LoggerFactory.getLogger(OrderItemProcessor.class);

    @Override
    public List<Order> process(Order[] orders) throws Exception {

        LOG.info("Order in process size " + orders.length);

        LOG.info(
                ((orders.length >= 1) ? orders[0] : "Nothing Found").toString());

        List<Order> modOrders = new ArrayList<>();
/*        if(orders.length <= 0){
            throw new Exception("No Orders Received");
        }*/

        for (Order order : orders) {

            LOG.info("Processing for ID " + order.getOrderid());

            switch (order.getStatus()){
                case SHIP_CREATED:
                    order.setStatus(Status.SHIP_TRANSMIT);
                    modOrders.add(order);
                    break;
                case SHIP_TRANSMIT:
                    order.setStatus(Status.SHIP_OUT_FOR_DELIVERY);
                    modOrders.add(order);
                    break;
                case SHIP_OUT_FOR_DELIVERY:
                    order.setStatus(Status.SHIP_DELIVERED);
                    modOrders.add(order);
                    break;
                case SHIP_DELIVERED:
                    order.setStatus(Status.COMPLETED);
                    modOrders.add(order);
                    break;

            }

            LOG.info("Processing Completed " + order);

            LOG.info("Processing completed for ID " + order.getOrderid());
        }
        return modOrders;
    }
}
