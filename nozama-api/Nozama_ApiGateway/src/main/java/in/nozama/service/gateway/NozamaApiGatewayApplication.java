package in.nozama.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NozamaApiGatewayApplication {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(NozamaApiGatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NozamaApiGatewayApplication.class, args);
	}
	
}
