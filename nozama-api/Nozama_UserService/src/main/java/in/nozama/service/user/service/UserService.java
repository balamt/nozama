package in.nozama.service.user.service;

import java.util.List;
import java.util.Optional;

import in.nozama.service.model.*;

public interface UserService {
	
	public List<User> getAllUser();

	public Optional<User> getUserById(Long userId);
	
	boolean addNewUser(User user);
	boolean validateUser(LoginCredentials loginCredentials);

}
