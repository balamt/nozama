package in.nozama.service.user.JwtHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("${jwt.secret}")
	private String jwtSecret;

	public static final long JWT_TOKEN_VALIDITY = 15 * 60 * 60;

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

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
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

	private String doGenerateToken(Map<String, Object> claims, String email) {
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = getUsernameFromToken(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
