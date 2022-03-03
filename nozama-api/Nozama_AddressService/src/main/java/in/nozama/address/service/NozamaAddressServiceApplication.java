package in.nozama.address.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories("in.nozama")
@EntityScan("in.nozama")
public class NozamaAddressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaAddressServiceApplication.class, args);
	}

}
