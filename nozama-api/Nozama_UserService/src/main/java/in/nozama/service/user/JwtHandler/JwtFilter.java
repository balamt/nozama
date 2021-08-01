package in.nozama.service.user.JwtHandler;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import in.nozama.service.user.exception.TokenNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtFilter extends GenericFilterBean {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
//		final HttpServletRequest request = (HttpServletRequest) req;
//		final String authorization = request.getHeader(HEADER_STRING);
//
//		if (authorization != null && authorization.startsWith(TOKEN_PREFIX)) {
//			try {
//				final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authorization.substring(7))
//						.getBody();
//				System.out.println("claims " + claims);
//				request.setAttribute("claims", claims);
//			} catch (final SignatureException e) {
//				throw new ServletException("401 - UNAUTHORIZED");
//			}
//			
//		}else {
//			throw new ServletException(new TokenNotFoundException("Token missing in the Request"));
//		}
		chain.doFilter(req, res);
	}
}