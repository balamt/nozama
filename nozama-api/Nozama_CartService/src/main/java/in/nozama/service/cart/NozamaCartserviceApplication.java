package in.nozama.service.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EntityScan("in.nozama")
@EnableJpaRepositories("in.nozama")
@SpringBootApplication
@EnableFeignClients
public class NozamaCartserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaCartserviceApplication.class, args);
	}

}
