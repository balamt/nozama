package in.nozama.service.user.jwtconfig;

import in.nozama.service.user.jwthandler.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.nozama.service.user.jwthandler.JwtFilter;


@Configuration
public class JwtConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public FilterRegistrationBean<JwtFilter> filterRegistrationBean() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(jwtFilter);
		//Need to add filter to Secure Paths
		filterRegistrationBean.addUrlPatterns("/user/*");
		filterRegistrationBean.addUrlPatterns("/user/**");
		filterRegistrationBean.addUrlPatterns("/user/swagger-ui/**");
		return filterRegistrationBean;
	}
}
