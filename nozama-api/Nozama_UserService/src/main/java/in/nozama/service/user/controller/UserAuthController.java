package in.nozama.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;

import in.nozama.service.model.UserCredentials;
import in.nozama.service.user.exception.AuthenticationException;
import in.nozama.service.user.jwthandler.JwtTokenUtil;
import in.nozama.service.user.model.TokenResponse;
import in.nozama.service.user.service.UserService;

public class UserAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserService userService;

//	@PostMapping(path = "/token")
	public ResponseEntity<?> createAuthToken(@RequestBody UserCredentials userCredentials)
			throws AuthenticationException {
		authenticate(userCredentials);
		final UserCredentials userCredential = userService.getUserByUsername(userCredentials.getEmail());
		final String token = jwtTokenUtil.generateToken(userCredential);
		return ResponseEntity.ok(new TokenResponse(token));
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
