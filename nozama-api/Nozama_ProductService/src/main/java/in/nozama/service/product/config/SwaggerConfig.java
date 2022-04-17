package in.nozama.service.product.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public GroupedOpenApi productApi(){    	
    	return GroupedOpenApi.builder().group("product").pathsToMatch("/product/**").build();
    }
    
    @Bean
    public GroupedOpenApi categoryApi(){    	
    	return GroupedOpenApi.builder().group("category").pathsToMatch("/category/**").build();
    }    
}
