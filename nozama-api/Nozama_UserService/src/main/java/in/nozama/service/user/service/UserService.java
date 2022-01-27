package in.nozama.service.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.model.UserCredentials;
import in.nozama.service.user.model.User;

public interface UserService extends UserDetailsService {
	
	public List<UserResponse> getAllUser();

	public Optional<UserResponse> getUserById(Long userId);

	User addNewUser(CreateUserRequest user);

	boolean validateUser(UserCredentials loginCredentials);

	User getUserByEmail(String email);
	
	UserCredentials getUserByUsername(String email);

}
