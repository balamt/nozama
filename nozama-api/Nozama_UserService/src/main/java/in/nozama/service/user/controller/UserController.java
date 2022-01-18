package in.nozama.service.user.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.dto.view.UserModelView;
import in.nozama.service.user.exception.UserNotFoundException;
import in.nozama.service.user.model.JwtAuthenticationToken;
import in.nozama.service.user.model.User;
import in.nozama.service.user.model.UserCredentials;
import in.nozama.service.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
/*@CrossOrigin(origins = {"http://localhost:4200", "http://balahp:4200","http://localhost:4200/"})*/
@RequestMapping(value = "/user", produces = "application/hal+json")
public class UserController {

    @Autowired
    UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    HttpHeaders headers;

    @RolesAllowed("PRIME")
    @GetMapping(value = "/all")
    @JsonView(UserModelView.PublicView.class)
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        final String uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();

        List<UserResponse> users = userService.getAllUser();

        // By This we can add self link to the user, Note we need to extend User class
        // with ResourceSupport
//        for (User usr : users) {
//            Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(usr.getUserid()).withSelfRel();
//            usr.add(selfLink);
//        }
//
//        final CollectionModel<User> userResources = CollectionModel.of(users);
//        userResources.add(Link.of(uri, "self"));
//        return ResponseEntity.ok(userResources);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @JsonView(UserModelView.PublicView.class)
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "id") Long userId) throws UserNotFoundException {
    	Optional<UserResponse> user = userService.getUserById(userId);
    	if(user.isPresent()) {
    		return ResponseEntity.ok(user.get());
    	}
    	throw new UserNotFoundException(String.format("Could not find such user id %d", userId));
        //return ResponseEntity.ok(null);
    }

    @PostMapping("/signup")
    @JsonView(UserModelView.ProtectedView.class)
    public ResponseEntity<UserResponse> addUser(@RequestBody CreateUserRequest user) {
        //Encoding the password using BCrypt Encoder

        System.out.println(user.toString());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User createdUser = userService.addNewUser(user);
        headers = new HttpHeaders();
        headers.set("userid", createdUser.getUserid().toString());

        return (createdUser != null && createdUser.getUserid() > 0) ? new ResponseEntity<>(headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    @JsonView(UserModelView.PublicView.class)
    public ResponseEntity login(@RequestBody UserCredentials loginCredentials) {
        boolean isValidUser = userService.validateUser(loginCredentials);
        if (isValidUser) {
            User user = userService.getUserByEmail(loginCredentials.getEmail());
            headers = new HttpHeaders();
            JwtAuthenticationToken token =
                    new JwtAuthenticationToken(Jwts.builder().setSubject(user.getEmail()).claim("roles", user.getUsertype())
                            .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "nozama").compact());
            headers.set("token", token.getToken());
            headers.set("userid", user.getUserid().toString());
            return new ResponseEntity(user, headers, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    public ResponseEntity<String> testServer() {
        return ResponseEntity.ok("User service up and running...");
    }
}
