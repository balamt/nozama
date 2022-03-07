package in.nozama.service.user.jwthandler;

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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.nozama.service.user.exception.TokenNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	public static final String UNAUTHORIZED = "Unauthorized";

	public static final String CLAIMS = "claims";

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	public JwtFilter(ApplicationContext ctx) {
		this.jwtTokenUtil = ctx.getBean(JwtTokenUtil.class);
		this.userDetailsService = ctx.getBean(UserDetailsService.class);
	}
		
	private boolean isAllowedPath(HttpServletRequest request) {
		return (request.getServletPath().contains("/user/email")
				|| request.getServletPath().contains("/user/signup")
				|| request.getServletPath().contains("/user/status")
				|| request.getServletPath().contains("/user/login")
				|| request.getServletPath().contains("/error")
				|| request.getServletPath().contains("/swagger-ui")
				|| request.getServletPath().contains("/api-docs/")
				|| request.getServletPath().endsWith("/actuator/info"));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String authorization = request.getHeader(JwtTokenUtil.HEADER_STRING);
		final boolean isPermittedUrl = isAllowedPath(request);
		if (isPermittedUrl) {
			chain.doFilter(request, response);
		}

		// Anything is before this line, will not required token in the request.
		// Anything to proceed with token needs to be handled from here
		if (authorization != null && authorization.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
			final String token = authorization.replaceAll(JwtTokenUtil.TOKEN_PREFIX, "");
			try {
				if (jwtTokenUtil.validateToken(token, request)) {
					String username = jwtTokenUtil.getUsernameFromToken(token);
					final Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(token,
							SecurityContextHolder.getContext().getAuthentication(), userDetails);
					request.setAttribute(JwtFilter.CLAIMS, claims);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					chain.doFilter(request, response);
				}
			} catch (IllegalArgumentException iae) {
				LOGGER.error("an error occured during getting username from token", iae);
			} catch (ExpiredJwtException ejte) {
				final String expiredMsg = ejte.getMessage();
				LOGGER.warn("Token is expired and not valid anymore", ejte);
				final String msg = (expiredMsg != null) ? expiredMsg : JwtFilter.UNAUTHORIZED;
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
			} catch (SignatureException se) {
				LOGGER.error("Authentication Failed. Username or password not valid", se);
			}
		} else if(!isPermittedUrl) {
			throw new ServletException(new TokenNotFoundException("Token missing in the Request"));
		}
	}
}