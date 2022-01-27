package in.nozama.scheduler.delivery.writer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import in.nozama.service.entity.Order;
import in.nozama.service.util.ServiceUrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrderItemWriter implements ItemWriter<Order> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderItemWriter.class);

    @Value("${nozama.api.gateway.serviceid}")
    private String nozamaApiGateway;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void write(List<? extends Order> orders){
        for (Order order : orders) {
            LOG.info("Order Calling Rest ");
            //Refer the Shipment Service Controller class for understanding the purpose of this codes
            Application application = eurekaClient.getApplication(nozamaApiGateway);
            InstanceInfo instanceInfo = application.getInstances().get(0);

            String orderServiceURL = ServiceUrlBuilder
                    .constructUrl(nozamaApiGateway,instanceInfo.getPort(),"/order/updatae",null);

            restTemplate.put(orderServiceURL, order);
            LOG.info("Order PUT to Order " + order.toString());
        }
    }
}
