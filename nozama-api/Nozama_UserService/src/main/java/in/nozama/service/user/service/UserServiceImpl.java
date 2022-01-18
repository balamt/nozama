package in.nozama.service.user.service;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.user.mapper.UserMapper;
import in.nozama.service.user.model.User;
import in.nozama.service.user.model.UserCredentials;
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
	
	@Autowired UserMapper userMapper;

	@Autowired UserRepositoryCustomDao userRepositoryCustomDao;

	@Override
	public List<UserResponse> getAllUser() {
		return userMapper.map(userRepository.findAll());
	}

	@Override
	public Optional<UserResponse> getUserById(Long userId) {
		return userMapper.map(userRepository.findById(userId));
	}

	@Override
	public User addNewUser(CreateUserRequest user){
		User userPersisted = userRepository.save(userMapper.map(user));
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
