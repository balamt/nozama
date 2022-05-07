package in.nozama.nozamauserauthservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unautorizedHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	public JwtAuthenticationFilter authenticationTokenFilerBean() {
		return new JwtAuthenticationFilter(getApplicationContext());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/health/**").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/actuator/prometheus").permitAll()
				.antMatchers("/actuator/**").permitAll()				
				.antMatchers("/auth/status", "/auth/token").permitAll()
				.antMatchers("/error").permitAll()
				.antMatchers("/userauth-sw/**", "/userauth-api/**", "/swagger-ui/**",
						"/webjars/**", "/v3/**").permitAll()
				.anyRequest()
				.authenticated().and()
				.httpBasic().and()
				.exceptionHandling()
				.authenticationEntryPoint(unautorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationTokenFilerBean(), UsernamePasswordAuthenticationFilter.class);
	}
	
//	 @Bean
//	  CorsConfigurationSource corsConfigurationSource() 
//	  {
//	      final CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(ImmutableList.of("http://localhost:3000/","http://nozama:3000/","http://127.0.0.1:3000/","http://0.0.0.0:3000/","*"));
//	        configuration.setAllowedMethods(ImmutableList.of("OPTIONS", "HEAD",
//	                "GET", "POST", "PUT", "DELETE", "PATCH"));
//	        // setAllowCredentials(true) is important, otherwise:
//	        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//	        configuration.setAllowCredentials(true);
//	        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//	        // will fail with 403 Invalid CORS request
//	        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
//	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return source;
//	  }

}
