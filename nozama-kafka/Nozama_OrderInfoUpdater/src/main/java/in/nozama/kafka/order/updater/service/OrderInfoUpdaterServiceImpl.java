package in.nozama.kafka.order.updater.service;

import in.nozama.service.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoUpdaterServiceImpl implements OrderInfoUpdaterService {

    private final static Logger LOG = LoggerFactory.getLogger(OrderInfoUpdaterServiceImpl.class);

    @Autowired
    KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${kafka.topic}")
    private String ORDER_INFO_TPOIC_NAME;

    @Override
    public void sendOrderInfo(Order order){
        LOG.info(String.format("Sending Kafka message to %s with Order Id %d",
                ORDER_INFO_TPOIC_NAME,
                order.getOrderid()));
        kafkaTemplate.send(ORDER_INFO_TPOIC_NAME,  order);
    }

}
