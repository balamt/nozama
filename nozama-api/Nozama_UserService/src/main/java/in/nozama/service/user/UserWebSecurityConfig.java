package in.nozama.service.user;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import in.nozama.service.user.JwtHandler.JwtFilter;
import in.nozama.service.user.service.UserService;

/**
 * https://medium.com/@akhileshanand/spring-boot-api-security-with-jwt-and-role-based-authorization-fea1fd7c9e32
 * 
 * @author balam
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private UserService userService;
	
	public UserWebSecurityConfig() {
		super();
	}

	public UserWebSecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
	}
	
	@Bean
	@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManagerBean();
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf()
		.ignoringAntMatchers("/user/**").disable().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/user/**","/auth/**").permitAll()
				.anyRequest().authenticated();				

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/user/login", "/user/signup","/auth");
	}

}
