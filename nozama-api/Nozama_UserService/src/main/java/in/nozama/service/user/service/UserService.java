package in.nozama.service.user.service;

import in.nozama.service.model.UserCredentials;
import in.nozama.service.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	
	public List<User> getAllUser();

	public Optional<User> getUserById(Long userId);

	User addNewUser(User user);

	boolean validateUser(UserCredentials loginCredentials);

	User getUserByEmail(String email);
	
	UserDetails getUserByUsername(String email);

}
