package in.nozama.service.user;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@EnableWebMvc
public class UserSwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public GroupedOpenApi userApi(){    	
    	return GroupedOpenApi.builder().group("user").pathsToMatch("/user/**").build();
    }
    
    @Bean
    public GroupedOpenApi authApi(){    	
    	return GroupedOpenApi.builder().group("auth").pathsToMatch("/auth/**").build();
    }    
}
