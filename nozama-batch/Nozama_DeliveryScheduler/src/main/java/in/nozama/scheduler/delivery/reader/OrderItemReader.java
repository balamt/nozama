package in.nozama.scheduler.delivery.reader;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import in.nozama.service.entity.Order;
import in.nozama.service.util.ServiceUrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderItemReader implements ItemReader<Order[]> {

    private final static Logger LOG = LoggerFactory.getLogger(OrderItemReader.class);

    @Value("${nozama.api.gateway.serviceid}")
    private String nozamaApiGateway;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    private Order[] orders;

    @Override
    public Order[] read() {
        orders = null;
        orders = fetchOrdersToProcess();
        return orders;
    }

    private Order[] fetchOrdersToProcess() {

        //Refer the Shipment Service Controller class for understanding the purpose of this codes
        Application application = eurekaClient.getApplication(nozamaApiGateway);
        InstanceInfo instanceInfo = application.getInstances().get(0);


        String orderServiceURL = ServiceUrlBuilder
                .constructUrl(instanceInfo.getHostName(),instanceInfo.getPort(),"/order/ship",null);

        LOG.info("******************************Inside fetchOrdersToProcess in Reader *******************************");
        ResponseEntity<Order[]> response = restTemplate.getForEntity(orderServiceURL,
                Order[].class);
        Order[] ordersToProcess = response.getBody();
        return ordersToProcess;
    }

    private boolean orderDataIsNotInitialized() {
        return this.orders == null;
    }
}
