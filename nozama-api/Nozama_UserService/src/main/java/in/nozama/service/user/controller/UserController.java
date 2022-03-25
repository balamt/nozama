package in.nozama.service.user.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.dto.view.UserModelView;
import in.nozama.service.model.NozamaConst;
import in.nozama.service.model.UserCredentials;
import in.nozama.service.user.exception.UserAlreadyExistsException;
import in.nozama.service.user.exception.UserNotFoundException;
import in.nozama.service.user.jwthandler.JwtTokenUtil;
import in.nozama.service.user.model.User;
import in.nozama.service.user.service.UserService;
import in.nozama.service.user.service.proxy.AddressServiceProxy;	

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
/*
 * @CrossOrigin(origins = {"http://localhost:4200",
 * "http://balahp:4200","http://localhost:4200/"})
 */
@RequestMapping(value = "/user", produces = "application/hal+json")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	AddressServiceProxy addressService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	HttpHeaders headers;

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@GetMapping(value = "/all")
	@JsonView(UserModelView.PublicView.class)
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		List<UserResponse> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/view/{id}")
	@JsonView(UserModelView.PublicView.class)
	public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "id") Long userId)
			throws UserNotFoundException {
		Optional<UserResponse> user = userService.getUserById(userId);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		throw new UserNotFoundException(String.format("Could not find such user id %d", userId));
	}

	@PostMapping("/email/{username}")
	public ResponseEntity<UserCredentials> getUserById(@PathVariable(name = "username") String email)
			throws UserNotFoundException {
		Optional<UserCredentials> user = Optional.ofNullable(userService.getUserByUsername(email));
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		throw new UserNotFoundException(String.format("Could not find such user id %s", email));
	}

	@PostMapping("/signup")
	@JsonView(UserModelView.ProtectedView.class)
	public ResponseEntity<Object> addUser(@RequestBody CreateUserRequest user) throws UserAlreadyExistsException {
		// Encoding the password using BCrypt Encoder
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User createdUser = null;
		try {
			createdUser = userService.addNewUser(user);
			headers = new HttpHeaders();
			if (createdUser != null) {
				if(user.getAddress() != null) {
					user.getAddress().setUserId(createdUser.getUserid());
					addressService.addAddress(user.getAddress());
				}
				headers.set(JwtTokenUtil.USERID, createdUser.getUserid().toString());
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			if (e.getMessage().contains("constraint")) {
				throw new UserAlreadyExistsException(String.format("User %s exists, Have you forgot Password? Visit %s",
						user.getEmail(), NozamaConst.FORGOT_PASSWORD_URL));
			}
		}
		return (createdUser != null && createdUser.getUserid() > 0) ? new ResponseEntity<>(headers, HttpStatus.CREATED)
				: new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@PostMapping("/login")
	@JsonView(UserModelView.PublicView.class)
	public ResponseEntity login(@RequestBody UserCredentials loginCredentials) {
		boolean isValidUser = userService.validateUser(loginCredentials);
		if (isValidUser) {
			User user = userService.getUserByEmail(loginCredentials.getEmail());
			headers = new HttpHeaders();
			String token = jwtTokenUtil.generateToken(userService.getUserByUsername(loginCredentials.getEmail()));
			headers.set(JwtTokenUtil.HEADER_TOKEN_STRING, token);
			headers.set(JwtTokenUtil.USERID, user.getUserid().toString());
			return new ResponseEntity(user, headers, HttpStatus.OK);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@GetMapping("/status")
	public ResponseEntity<String> testServer() {
		return ResponseEntity.ok("User service up and running...");
	}
}
