package in.nozama.service.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("in.nozama.service.cart.model")
@SpringBootApplication
public class NozamaCartserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaCartserviceApplication.class, args);
	}

}
