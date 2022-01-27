package in.nozama.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class NozamaConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaConfigServiceApplication.class, args);
	}

}
