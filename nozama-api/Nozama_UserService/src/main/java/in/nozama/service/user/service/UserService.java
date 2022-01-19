package in.nozama.service.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.user.model.User;
import in.nozama.service.user.model.UserCredentials;

public interface UserService extends UserDetailsService {
	
	public List<UserResponse> getAllUser();

	public Optional<UserResponse> getUserById(Long userId);

	User addNewUser(CreateUserRequest user);

	boolean validateUser(UserCredentials loginCredentials);

	User getUserByEmail(String email);
	
	UserDetails getUserByUsername(String email);

}
