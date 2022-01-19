package in.nozama.service.user;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import in.nozama.service.user.JwtHandler.JwtFilter;
import in.nozama.service.user.service.UserService;

/**
 * https://medium.com/@akhileshanand/spring-boot-api-security-with-jwt-and-role-based-authorization-fea1fd7c9e32
 * 
 * @author balam
 *
 */
@Configuration
@EnableWebSecurity(debug=true)
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
			return super.authenticationManagerBean();
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf()
		.ignoringAntMatchers("/user/**").disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/user/**","/auth/**", "/documentation/**", "/v3/**", "/actuator/**", "/h2-console/**", "/profile/**", "/favicon.ico", "/sw/**").permitAll()
				.anyRequest().authenticated();				

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/user/login", "/user/signup","/auth","/auth/token","/error", "/sw");
	}
	
	@Bean
    @Primary
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
