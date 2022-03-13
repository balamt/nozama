package in.nozama.nozamauserauthservice.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import in.nozama.service.model.UserCredentials;
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
	public static final String HEADER_TOKEN_STRING = "token";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORITIES_KEY = "roles";
	public static final long JWT_TOKEN_VALIDITY = ((5 * 60) * 60) * 10;

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
		if(token.startsWith(TOKEN_PREFIX)) {
			token = token.replace(TOKEN_PREFIX, "");
		}
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = new Date(getExpirationDateFromToken(token).getTime() + JWT_TOKEN_VALIDITY);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		if(userDetails.getAuthorities() != null && !userDetails.getAuthorities().isEmpty()) {
			claims.put(AUTHORITIES_KEY, StringUtils.collectionToCommaDelimitedString(userDetails.getAuthorities()));
		}else {
			claims.put(AUTHORITIES_KEY, "ROLE_BASIC");
		}
		return doGenerateToken(claims, userDetails.getUsername());
	}

	public String generateToken(UserCredentials userCredentials) {
		List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<>();
		for (String role : userCredentials.getRoles()) {
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_" + role));
		}

		return generateToken(new org.springframework.security.core.userdetails.User(userCredentials.getEmail(),
				userCredentials.getPassword(), grantedAuthority));
	}

	private String doGenerateToken(Map<String, Object> claims, String email) {
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String email = getUsernameFromToken(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth,
			final UserDetails userDetails) {
		final var jwtParser = Jwts.parser().setSigningKey(jwtSecret);
		final var claimsJws = jwtParser.parseClaimsJws(token);
		final var claims = claimsJws.getBody();
		final var authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new).toList();
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

}
