package in.nozama.service.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.model.UserCredentials;
import in.nozama.service.user.exception.UserAlreadyExistsException;
import in.nozama.service.user.model.User;

@Service
public interface UserService extends UserDetailsService {
	
	public List<UserResponse> getAllUser();

	public Optional<UserResponse> getUserById(Long userId);

	User addNewUser(CreateUserRequest user) throws UserAlreadyExistsException;

	boolean validateUser(UserCredentials loginCredentials);

	User getUserByEmail(String email);
	
	UserCredentials getUserByUsername(String email);

	public void UpdateAddressId(CreateUserRequest user, Long addressId);

	public UserResponse removeUserById(Long userId);

}
