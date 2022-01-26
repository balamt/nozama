package in.nozama.service.user.JwtHandler;

import java.io.IOException;
import java.io.Serializable;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import in.nozama.service.model.UserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("${jwt.secret:nozama}")
	private String jwtSecret;

	public static final String HEADER_STRING = "Authorization";
	public static final String HEADER_TOKEN_STRING = "token";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORITIES_KEY = "roles";
	public static final String EXPIRED_ATTRB = "expired";
	public static final long JWT_TOKEN_VALIDITY = ((1 * 60) * 60);

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public Claims getAllClaimsFromToken(String token) {
		if (token.startsWith(TOKEN_PREFIX)) {
			token = token.replace(TOKEN_PREFIX, "");
		}
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		List<String> roles = new ArrayList<>();
		if (userDetails.getAuthorities() != null) {
			for (GrantedAuthority authority : userDetails.getAuthorities()) {
				roles.add("ROLE_" + authority.getAuthority().trim().toUpperCase());
			}
		}
		claims.put("roles", roles);
		String username = userDetails.getUsername();
		claims.put("username", username);

		return doGenerateToken(claims, userDetails.getUsername());

	}

	public String generateToken(UserCredentials userCredentials) {

		List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<SimpleGrantedAuthority>();
		for (String role : userCredentials.getRoles()) {
			grantedAuthority.add(new SimpleGrantedAuthority(role));
		}

		return generateToken(new org.springframework.security.core.userdetails.User(userCredentials.getEmail(),
				userCredentials.getPassword(), grantedAuthority));

	}

	private String doGenerateToken(Map<String, Object> claims, String email) {
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 10))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = getUsernameFromToken(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean validateToken(String token, HttpServletRequest httpServletRequest) throws IOException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT exception");
		} catch (IllegalArgumentException ex) {
			System.out.println("Jwt claims string is empty");
		}
		return false;
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
