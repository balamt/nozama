package in.nozama.nozamauserauthservice.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.nozamauserauthservice.proxy.UserServiceProxy;

@RefreshScope
@RestController
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	UserServiceProxy userService;

	@RequestMapping("/status")
	public ResponseEntity testServer() {
		return userService.testServer();
	}
	
	@RolesAllowed("ADMIN")
	@RequestMapping("/users")
	public ResponseEntity getUsers() {
		return ResponseEntity.ok(userService.getUserById((long) 2));
	}

}
