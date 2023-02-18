package in.nozama.nozamauserauthservice.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@EnableWebMvc
public class UserAuthSwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public GroupedOpenApi authApi(){    	
    	return GroupedOpenApi.builder().group("auth").pathsToMatch("/auth/**").build();
    }
    
    @Bean
    public GroupedOpenApi authHealthApi(){    	
    	return GroupedOpenApi.builder().group("auth-health").pathsToMatch("/auth-health/**").build();
    }
}
