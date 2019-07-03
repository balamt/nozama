package in.nozama.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class NozamaApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaApiGatewayApplication.class, args);
	}
	
	@Bean
	public NozamaSimpleFilter nozamaSimpleFilter() {
		return new NozamaSimpleFilter();
	}

}
