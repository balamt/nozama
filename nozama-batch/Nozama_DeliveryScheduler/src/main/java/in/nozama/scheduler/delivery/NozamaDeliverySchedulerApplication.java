package in.nozama.scheduler.delivery;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * https://howtodoinjava.com/spring-batch/java-config-multiple-steps/
 * Refer abour for tasklet based batch job
 *
 * https://www.opencodez.com/java/spring-batch-with-spring-boot.htm
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@EntityScan("in.nozama")
public class NozamaDeliverySchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NozamaDeliverySchedulerApplication.class, args);
	}

}
