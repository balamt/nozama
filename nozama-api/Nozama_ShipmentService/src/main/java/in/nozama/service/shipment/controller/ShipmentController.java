package in.nozama.service.shipment.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import in.nozama.service.shipment.exception.ShipmentAlreadyCreatedException;
import in.nozama.service.shipment.exception.ShipmentNotFoundException;
import in.nozama.service.shipment.model.Track;
import in.nozama.service.shipment.model.TrackCompositKeys;
import in.nozama.service.shipment.service.ShipmentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RefreshScope
@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    private static final Logger LOG = LoggerFactory.getLogger(ShipmentController.class);

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    /**
     * Check the application.properties -- we have used the service id of Api Gateway,
     * So that we can used Gateway to talk to the Order Service for getting the data.
     *
     * If you do not want to use the api-gateway, we can use the service id of the order service, and get its instance
     * info below in the createNewShipment method.
     *
     * For Example if you want to use service id of order service, change the value in application.properties
     *      nozama-orderservice
     *      when you change this, based on the eureka server registery the IP and Post of this will be populated to use
     *      in restTemplate.
     *     Reference : https://dzone.com/articles/microservices-communication-service-to-service
     *
     */
    @Value("${nozama.api.gateway.serviceid}")
    private String nozamaApiGateway;
    
    @GetMapping
    public ResponseEntity<List<Track>> getAllShipements() {
    	List<Track> shipmentDetail = shipmentService.getAll();
        return ResponseEntity.ok(shipmentDetail);
    }
    
    @PostMapping("/create/{orderId}")
    public ResponseEntity<Track> createNewShipment(@PathVariable(name = "orderId") Long orderId) throws ShipmentAlreadyCreatedException {

        /**
         * By default the create shipment will receive only the order id, not the complete object
         * Call Order Service and get the complete details via restTemplate
         * including the current status
         */

        //Step 1. Get the Order Service Instance Id From Eureka
        Application application = eurekaClient.getApplication(nozamaApiGateway);
        //Step 2. Get the IP Address of the Instance
        InstanceInfo instanceInfo = application.getInstances().get(0);
        /**
         * Construct the URL using the serviceId and Port (Note Since RestTemplate has LoadBalance,
         * we have to use the serviceid in the request. eg: https://nozama-orderservice:8083/order/2
         * if we are not having load balance we can use the ip Address and port from the instanceInfo object.
         * Example:
         *  instanceInfo.getIPAddr() --- This will give the IP Address of the Order Service from the Eureka Server
         *  instanceInfo.getPort() ----- This will give the Port of the Order Service from the Eureka Server.
         *  Below code will actually get the IpAddress and construct URL as http://127.0.0.1:8083/order/2
         *  String orderServiceGetOrderURL = MessageFormat.format("http://{0}:{1}/order/{2}",
         *                 instanceInfo.getIPAddr(),
         *                 String.valueOf(instanceInfo.getPort()),
         *                 String.valueOf(orderId));
         */
        String orderServiceGetOrderURL = ServiceUrlBuilder.constructUrl(nozamaApiGateway, instanceInfo.getPort(),
                "/order/", String.valueOf(orderId));

        if(LOG.isDebugEnabled()) {
            LOG.debug(MessageFormat.format("Order Service Instance URL for GetOrderById : {0}",
                    orderServiceGetOrderURL));
        }

        Order orderFromSvc = restTemplate.getForObject(orderServiceGetOrderURL, Order.class);

        if(LOG.isDebugEnabled()) {
            LOG.debug(MessageFormat.format("Order Service Response for OrderId : {0} \n {1}",
                    orderId, orderFromSvc.toString()));
        }

        Track shipmentDetail = shipmentService.createShipment(orderFromSvc);
        return ResponseEntity.ok(shipmentDetail);
    }

    @GetMapping("/track/{orderId}")
    public ResponseEntity<Track> trackShipment(@PathVariable(name = "orderId") Long orderId) throws ShipmentNotFoundException {
        Application application = eurekaClient.getApplication(nozamaApiGateway);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String orderServiceGetOrderURL = ServiceUrlBuilder.constructUrl(nozamaApiGateway, instanceInfo.getPort(),
                "/order/", String.valueOf(orderId));
        Order orderFromSvc = restTemplate.getForObject(orderServiceGetOrderURL, Order.class);
        TrackCompositKeys trackCompositKeys = new TrackCompositKeys(orderFromSvc.getUser(),orderFromSvc);
        Track shipmentDetail = shipmentService.getShipmentDetails(trackCompositKeys);
        return ResponseEntity.ok(shipmentDetail);
    }

    @ExceptionHandler
    void trackNotFoundExceptionHandler(ShipmentNotFoundException snfe, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), snfe.getMessage());
    }

}
