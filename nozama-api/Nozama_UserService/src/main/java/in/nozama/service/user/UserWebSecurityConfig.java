package in.nozama.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.nozama.service.user.jwthandler.JwtFilter;
import jakarta.annotation.Resource;

/**
 * https://medium.com/@akhileshanand/spring-boot-api-security-with-jwt-and-role-based-authorization-fea1fd7c9e32
 * 
 * @author balam
 *
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalAuthentication
public class UserWebSecurityConfig extends WebSecurityConfiguration {

	@Resource
	private UserDetailsService userDetailsService;

	public UserWebSecurityConfig() {
		super();
	}

	@Bean
	@Primary
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	public JwtFilter authenticationTokenFilerBean() {
		return new JwtFilter(getApplicationContext());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/user/signup").permitAll()
		.antMatchers("/user/status").permitAll()
		.antMatchers("/actuator/**").permitAll()
				.antMatchers("/user/**", "/auth/**", "/documentation/**", "/v3/**", "/actuator/**", "/h2-console/**",
						"/profile/**", "/favicon.ico", "/user-sw/**", "/user-api/**")
				.permitAll().anyRequest().authenticated().and().exceptionHandling();
		http.addFilterBefore(authenticationTokenFilerBean(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/user/login","/user/status", "/user/signup", "/auth", "/auth/token", "/error", "/user-sw",
				"/user-api/**", "/v3/api-docs/**", "/actuator/**");
	}

//	protected JwtAuthenticationConverter authenticationConverter() {
//        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        authoritiesConverter.setAuthorityPrefix("");
//        authoritiesConverter.setAuthoritiesClaimName(AUTHORITIES_CLAIM_NAME);
//
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
//        return converter;
//    }

}
