package in.nozama.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.service.user.JwtHandler.JwtTokenUtil;
import in.nozama.service.user.exception.AuthenticationException;
import in.nozama.service.user.model.TokenResponse;
import in.nozama.service.user.model.UserCredentials;
import in.nozama.service.user.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class UserAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserService userService;

	@PostMapping(path = "/token")
	public ResponseEntity<?> createAuthToken(@RequestBody UserCredentials userCredentials)
			throws AuthenticationException {
		authenticate(userCredentials);
		final UserDetails userDetails = userService.getUserByUsername(userCredentials.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
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
