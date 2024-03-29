package in.nozama.nozamauserauthservice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.nozama.nozamauserauthservice.util.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	TokenProvider tokenProvider;

	public JwtAuthenticationFilter(ApplicationContext ctx) {
		this.tokenProvider = ctx.getBean(TokenProvider.class);
		this.userDetailsService = ctx.getBean(UserDetailsService.class);
	}

	private boolean isAllowedPath(HttpServletRequest request) {
		return (request.getServletPath().contains("/actuator") 
				|| request.getServletPath().contains("/auth/")
				|| request.getServletPath().contains("/prometheus")
				|| request.getServletPath().contains("/swagger-ui")
				|| request.getServletPath().contains("/api-docs/")
				|| request.getServletPath().endsWith("/actuator/info")
				|| request.getServletPath().contains("/error") );
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final boolean isPermittedUrl = isAllowedPath(request);
		if (isPermittedUrl) {
			chain.doFilter(request, response);
		}

		String header = request.getHeader(TokenProvider.HEADER_STRING);
		String username = null;
		String authToken = null;
		if (header != null && header.startsWith(TokenProvider.TOKEN_PREFIX)) {
			authToken = header.replace(TokenProvider.TOKEN_PREFIX, "");
			try {
				username = tokenProvider.getUsernameFromToken(authToken);
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					validateAndAuthenticateUser(request, username, authToken, userDetails);
				}

			} catch (IllegalArgumentException iae) {
				LOGGER.error("an error occured during getting username from token", iae);
			} catch (ExpiredJwtException ejte) {
				LOGGER.warn("Token is expired and not valid anymore", ejte);
			} catch (SignatureException se) {
				LOGGER.error("Authentication Failed. Username or password not valid", se);
			}
		} else {
			LOGGER.warn("couldn't find bearer string, will ignore the header");
		}
		chain.doFilter(request, response);
	}

	private void validateAndAuthenticateUser(HttpServletRequest request, String username, String authToken,
			UserDetails userDetails) {
		if (tokenProvider.validateToken(authToken, userDetails)) {
			UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(authToken,
					SecurityContextHolder.getContext().getAuthentication(), userDetails);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			logger.info(String.format("authenticated user {%s}, setting security context", username));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

}
