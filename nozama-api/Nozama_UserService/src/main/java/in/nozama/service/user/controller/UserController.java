package in.nozama.service.user.controller;

import in.nozama.service.model.JwtAuthenticationToken;
import in.nozama.service.model.LoginCredentials;
import in.nozama.service.model.User;
import in.nozama.service.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = "application/hal+json")
public class UserController {

	@Autowired
	UserService userService;
	
	HttpHeaders headers;

	@GetMapping(value = "/all")
	public ResponseEntity<Resources<User>> getAllUsers() {
		final String uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();

		List<User> users = userService.getAllUser();

		// By This we can add self link to the user, Note we need to extend User class
		// with ResourceSupport
		for (User usr : users) {
			Link selfLink = ControllerLinkBuilder.linkTo(UserController.class).slash(usr.getUserid()).withSelfRel();
			usr.add(selfLink);
		}

		final Resources<User> userResources = new Resources<>(users);
		userResources.add(new Link(uri, "self"));
		return ResponseEntity.ok(userResources);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable(name = "id") Long userId) {
		return userService.getUserById(userId).get();
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Void> addUser(@RequestBody User user) {
		//Encoding the password using BCrypt Encoder
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		boolean isAdded = userService.addNewUser(user);
		headers = new HttpHeaders();

		return (isAdded) ? new ResponseEntity<>(headers, HttpStatus.CREATED)
				: new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationToken> login(@RequestBody LoginCredentials loginCredentials) {
		boolean isValidUser;
		isValidUser= userService.validateUser(loginCredentials);
		
		return (isValidUser)? new ResponseEntity<>(
				new JwtAuthenticationToken(Jwts.builder().setSubject(loginCredentials.getEmail()).claim("roles", "user")
						.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "nozama").compact()),
				HttpStatus.OK)
				: new ResponseEntity<>(new JwtAuthenticationToken(Jwts.builder().setSubject(loginCredentials.getEmail()).claim("roles", "user")
						.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "nozama").compact()), HttpStatus.NO_CONTENT);
}
}
