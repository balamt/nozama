package in.nozama.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
//@EnableEurekaClient -- Not required when we add the eureka discovery client dependency in the pom : Refer JAHNAVI
@SpringBootApplication
@EnableJpaRepositories("in.nozama")
@EntityScan("in.nozama")
public class NozamaUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaUserServiceApplication.class, args);
	}

}
