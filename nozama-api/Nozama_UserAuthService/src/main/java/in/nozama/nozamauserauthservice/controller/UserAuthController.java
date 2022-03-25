package in.nozama.nozamauserauthservice.controller;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
import in.nozama.service.model.ErrorResponse;
import in.nozama.service.model.Status;
import in.nozama.service.model.UserCredentials;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RefreshScope
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "*" }, allowedHeaders = "*")
@RequestMapping("/auth")
public class UserAuthController {

	private static final String INSTANCE_NAME = "UserAuthController";
	private static final Logger LOG = LoggerFactory.getLogger(INSTANCE_NAME);

	@Autowired
	UserServiceProxy userService;

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping("/status")
	@CircuitBreaker(name = INSTANCE_NAME, fallbackMethod = "fallbackTestServer")
	public ResponseEntity testServer() {
		return userService.testServer();
	}

	public ResponseEntity fallbackTestServer(Exception ise) {
		return new ResponseEntity("Service is Down", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RolesAllowed("ADMIN")
	@RequestMapping("/users")
	public ResponseEntity getUsers() throws ServiceException {
		return ResponseEntity.ok(userService.getUserById((long) 2));
	}

	@RequestMapping(path = "/token/renew", method = RequestMethod.POST)
	@CircuitBreaker(name = INSTANCE_NAME, fallbackMethod = "fallbackRenewToken")
	public ResponseEntity<?> renewToken(@RequestHeader(TokenProvider.HEADER_STRING) final String token)
			throws AuthenticationException, ServiceException {
		if (!tokenProvider.isTokenExpired(token)) {
			String username = tokenProvider.getUsernameFromToken(token);
			final UserCredentials userCred = userService.getUserByUsername(username).getBody();
			final String newToken = tokenProvider.generateToken(userCred);
			return ResponseEntity.ok().header(TokenProvider.HEADER_TOKEN_STRING, newToken)
					.header(TokenProvider.HEADER_STRING, newToken).body(new TokenResponse(newToken));
		}

		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new TokenResponse(token));
	}

	public ResponseEntity<?> fallbackRenewToken(Exception e) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatus(Status.ERROR);
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@RequestMapping(path = "/token", method = RequestMethod.POST)
	@CircuitBreaker(name = INSTANCE_NAME, fallbackMethod = "fallbackCreateAuthToken")
	public ResponseEntity<?> createAuthToken(@RequestBody UserCredentials userCredentials)  {
		
		String token = null;
		try {
			authenticate(userCredentials);
			final ResponseEntity<UserCredentials> username = userService.getUserByUsername(userCredentials.getEmail());
			LOG.info(username.getBody().toString());
			final UserCredentials userCred = username.getBody();
			token = tokenProvider.generateToken(userCred);
		} catch (Exception se) {
			LOG.error(String.format("Error : %s " , se.getMessage()));
			if(se instanceof AuthenticationException
					|| se instanceof InternalAuthenticationServiceException) {
				token = null;
				if(se.getMessage().toLowerCase().contains("userdetailsservice returned null")) {
					return  ResponseEntity.internalServerError().body(new ErrorResponse("Invalid Username! Have you forgot your Username?", Status.ERROR));
				}
				return  ResponseEntity.internalServerError().body(new ErrorResponse(se.getMessage(), Status.ERROR));
			}
		}
		if (token != null) {
			return ResponseEntity.ok().header(TokenProvider.HEADER_TOKEN_STRING, token)
					.header(TokenProvider.HEADER_STRING, String.format("%s %s", TokenProvider.TOKEN_PREFIX, token))
					.body(new TokenResponse(token));
		}
		return ResponseEntity.internalServerError().header(TokenProvider.HEADER_TOKEN_STRING, token)
				.header(TokenProvider.HEADER_STRING, String.format("%s %s", TokenProvider.TOKEN_PREFIX, token))
				.body(new TokenResponse(token));
	}

	public ResponseEntity<?> fallbackCreateAuthToken(Exception e) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage("Unable to reach User Service to verify the authenticity of User");
		response.setStatus(Status.ERROR);
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

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
