package in.nozama.service.user.service;

import in.nozama.service.model.LoginCredentials;
import in.nozama.service.model.User;
import in.nozama.service.user.repository.UserRepository;
import in.nozama.service.user.repository.UserRepositoryCustomDao;
import org.springframework.beans.factory.annotation.Autowired;
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
	public boolean addNewUser(User user){
		User userPersisted = userRepository.save(user); 
		return (userPersisted.getUserid() != null && userPersisted.getUserid() > 0);
	}

	@Override
	public boolean validateUser(LoginCredentials loginCredentials) {
		return userRepositoryCustomDao.findUserByEmailAndPassword(loginCredentials.getEmail(),loginCredentials.getPassword());
			}

}
