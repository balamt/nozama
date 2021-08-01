package in.nozama.service.user.service;

import in.nozama.service.model.UserCredentials;
import in.nozama.service.model.User;
import in.nozama.service.user.repository.UserRepository;
import in.nozama.service.user.repository.UserRepositoryCustomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepository;

	@Autowired UserRepositoryCustomDao userRepositoryCustomDao;

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User addNewUser(User user){
		User userPersisted = userRepository.save(user);
		System.out.println("User Service addNewUser " + userPersisted.toString());
		if(userPersisted.getUserid() != null && userPersisted.getUserid() > 0){
			return userPersisted;
		}
		else{
			return null;
		}
	}

	@Override
	public boolean validateUser(UserCredentials userCredentials) {
		return userRepositoryCustomDao.findUserByEmailAndPassword(userCredentials.getEmail(),userCredentials.getPassword());
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepositoryCustomDao.getUserByEmail(email);
	}

	@Override
	public UserDetails getUserByUsername(String email) {
		User user = userRepositoryCustomDao.getUserByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
	}	
	

}
