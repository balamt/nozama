package in.nozama.nozamauserauthservice.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORITIES_KEY = "roles";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = new Date(getExpirationDateFromToken(token).getTime() + JWT_TOKEN_VALIDITY * 100);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());

	}

	private String doGenerateToken(Map<String, Object> claims, String email) {
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = getUsernameFromToken(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
		final var jwtParser = Jwts.parser().setSigningKey(jwtSecret);
		final var claimsJws = jwtParser.parseClaimsJws(token);
		final var claims = claimsJws.getBody();
		final var authorities = 
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new UsernamePasswordAuthenticationToken(userDetails,"", authorities);
	}

}
