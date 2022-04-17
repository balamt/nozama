package in.nozama.nozamauserauthservice.config;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000/");
		config.addAllowedOrigin("http://nozama:3000/");
		config.addAllowedOrigin("http://127.0.0.1:3000/");
		config.addAllowedOrigin("http://0.0.0.0:3000/");
		config.addAllowedOriginPattern("http://localhost*");
		config.addAllowedOriginPattern("http://127.0.0.1*");
		config.addAllowedOriginPattern("http://0.0.0.0*");
		config.addAllowedOriginPattern("http://nozama*");
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addExposedHeader("userid");
		config.addExposedHeader("token");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("PATCH");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		config.setMaxAge(Duration.ofMinutes(10));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}
}
