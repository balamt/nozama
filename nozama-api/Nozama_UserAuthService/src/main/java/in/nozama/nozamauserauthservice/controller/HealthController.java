package in.nozama.nozamauserauthservice.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.nozamauserauthservice.service.UserService;

@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*")
@RefreshScope
@RestController
@RequestMapping(value = "/health", produces = "application/hal+json")
public class HealthController {

	@Autowired
	private UserService userService;

	@GetMapping("/status")
	public ResponseEntity getAuthServiceHealthStatus() {
		return ResponseEntity
				.ok(String.format("{\"status\":\"%s\",\"datetime\":\"%s\"}", "Up and Running..", LocalDateTime.now()));
	}

	@PreAuthorize("hasAnyRole('ADMIN','PRIME')")
	@GetMapping("/users")
	public ResponseEntity getAllUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}
	
	

}
