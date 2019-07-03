package in.nozama.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableEurekaClient	
@SpringBootApplication
@EnableJpaRepositories("in.nozama")
@EntityScan("in.nozama")
public class NozamaProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaProductServiceApplication.class, args);
	}

}
