package in.nozama.service.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories("in.nozama")
@EntityScan("in.nozama")
public class NozamaShipmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaShipmentServiceApplication.class, args);
	}

}
