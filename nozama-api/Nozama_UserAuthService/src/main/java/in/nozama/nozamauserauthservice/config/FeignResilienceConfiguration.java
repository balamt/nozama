package in.nozama.nozamauserauthservice.config;

//@Configuration
public class FeignResilienceConfiguration {

	/*
	 * @Bean public Customizer<Resilience4JCircuitBreakerFactory>
	 * circuitBreakerFactoryCustomizer() { CircuitBreakerConfig cbConfig =
	 * CircuitBreakerConfig.custom()
	 * .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED).
	 * slidingWindowSize(5)
	 * .failureRateThreshold(20.0f).waitDurationInOpenState(Duration.ofSeconds(5))
	 * .permittedNumberOfCallsInHalfOpenState(5).build(); return
	 * resilience4JCircuitBreakerFactory -> resilience4JCircuitBreakerFactory
	 * .configure(builder -> builder.circuitBreakerConfig(cbConfig),
	 * "UserServiceProxy"); }
	 * 
	 * @Bean public CircuitBreakerNameResolver circuitBreakerNameResolver() { return
	 * (feignClientName, target, method) -> Feign.configKey(target.type(), method);
	 * }
	 */

}
