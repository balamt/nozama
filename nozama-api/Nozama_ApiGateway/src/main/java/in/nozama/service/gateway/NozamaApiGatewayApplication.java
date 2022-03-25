package in.nozama.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("in.nozama")
public class NozamaApiGatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NozamaApiGatewayApplication.class, args);
	}
	
}
