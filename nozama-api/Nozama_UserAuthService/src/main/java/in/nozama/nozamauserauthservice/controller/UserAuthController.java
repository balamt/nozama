package in.nozama.nozamauserauthservice.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.nozamauserauthservice.exception.AuthenticationException;
import in.nozama.nozamauserauthservice.exception.ServiceException;
import in.nozama.nozamauserauthservice.model.TokenResponse;
import in.nozama.nozamauserauthservice.proxy.UserServiceProxy;
import in.nozama.nozamauserauthservice.util.TokenProvider;
import in.nozama.service.model.UserCredentials;

@RefreshScope
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	UserServiceProxy userService;

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping("/status")
	public ResponseEntity testServer() {
		return userService.testServer();
	}

	@RolesAllowed("ADMIN")
	@RequestMapping("/users")
	public ResponseEntity getUsers() throws ServiceException {
		return ResponseEntity.ok(userService.getUserById((long) 2));
	}

	@RequestMapping(path = "/token/renew", method = RequestMethod.POST)
	public ResponseEntity<?> renewToken(@RequestHeader(TokenProvider.HEADER_STRING) final String token)
			throws AuthenticationException, ServiceException {
		if (!tokenProvider.isTokenExpired(token)) {
			String username = tokenProvider.getUsernameFromToken(token);
			final UserCredentials userCred = userService.getUserByUsername(username).getBody();
			System.out.println(userCred.getRoles().get(0) + " Controller Role");
			final String newToken = tokenProvider.generateToken(userCred);
			return ResponseEntity.ok().header(TokenProvider.HEADER_TOKEN_STRING, newToken)
					.header(TokenProvider.HEADER_STRING, newToken).body(new TokenResponse(newToken));
		}

		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new TokenResponse(token));
	}

	@RequestMapping(path = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthToken(@RequestBody UserCredentials userCredentials)
			throws AuthenticationException, ServiceException {
		authenticate(userCredentials);
		final UserCredentials userCred = userService.getUserByUsername(userCredentials.getEmail()).getBody();
		final String token = tokenProvider.generateToken(userCred);
		return ResponseEntity.ok().header(TokenProvider.HEADER_TOKEN_STRING, token)
				.header(TokenProvider.HEADER_STRING, String.format("%s %s", TokenProvider.TOKEN_PREFIX, token))
				.body(new TokenResponse(token));
	}

	private void authenticate(UserCredentials userCredentials) throws AuthenticationException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword()));
		} catch (DisabledException de) {
			throw new AuthenticationException("USER_DISABLED", de);
		} catch (BadCredentialsException bce) {
			throw new AuthenticationException("INVALID_CREDENTIALS", bce);
		} catch (Exception e) {
			throw e;
		}

	}

}
