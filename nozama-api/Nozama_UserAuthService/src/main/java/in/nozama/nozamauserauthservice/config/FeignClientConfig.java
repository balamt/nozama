package in.nozama.nozamauserauthservice.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;

public class FeignClientConfig {
	
	@Bean
	public Logger.Level feignLogLevel(){
		return Logger.Level.FULL;
	}

}
