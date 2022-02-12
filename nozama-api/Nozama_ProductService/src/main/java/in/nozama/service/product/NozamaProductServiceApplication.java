package in.nozama.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import in.nozama.service.product.config.ProductImageUploadProperties;

@EnableDiscoveryClient
@EnableEurekaClient	
@SpringBootApplication
@EnableJpaRepositories("in.nozama")
@EntityScan("in.nozama")
@EnableConfigurationProperties({
	ProductImageUploadProperties.class
})
public class NozamaProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaProductServiceApplication.class, args);
	}

}
