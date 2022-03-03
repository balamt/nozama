package in.nozama.nozamauserauthservice.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Configuration
public class FeignResilienceConfiguration {

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> circuitBreakerFactoryCustomizer() {
		CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
				.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED).slidingWindowSize(5)
				.failureRateThreshold(20.0f).waitDurationInOpenState(Duration.ofSeconds(5))
				.permittedNumberOfCallsInHalfOpenState(5).build();
		return resilience4JCircuitBreakerFactory -> resilience4JCircuitBreakerFactory
				.configure(builder -> builder.circuitBreakerConfig(cbConfig), "UserServiceProxy");
	}

	@Bean
	public CircuitBreakerNameResolver circuitBreakerNameResolver() {
		return (feignClientName, target, method) -> Feign.configKey(target.type(), method);
	}

}
