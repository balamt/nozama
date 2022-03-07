package in.nozama.service.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.model.UserCredentials;
import in.nozama.service.user.mapper.UserMapper;
import in.nozama.service.user.model.User;
import in.nozama.service.user.repository.UserRepository;
import in.nozama.service.user.repository.UserRepositoryCustomDao;
import in.nozama.service.user.service.proxy.AddressServiceProxy;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRepositoryCustomDao userRepositoryCustomDao;
	
	@Autowired
	AddressServiceProxy addressService;

	@Override
	public List<UserResponse> getAllUser() {
		return userMapper.map(userRepository.findAll(), addressService);
	}

	@Override
	public Optional<UserResponse> getUserById(Long userId) {
		return userMapper.map(userRepository.findById(userId));
	}

	@Override
	public User addNewUser(CreateUserRequest user) {

			User userPersisted = userRepository.save(userMapper.map(user));
			if (userPersisted.getUserid() != null && userPersisted.getUserid() > 0) {
				return userPersisted;
			}

		return null;
	}

	@Override
	public boolean validateUser(UserCredentials userCredentials) {
		return userRepositoryCustomDao.findUserByEmailAndPassword(userCredentials.getEmail(),
				userCredentials.getPassword());
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepositoryCustomDao.getUserByEmail(email);
	}

	@Override
	public UserCredentials getUserByUsername(String email) {
		User user = userRepositoryCustomDao.getUserByEmail(email);
		List<String> list = new ArrayList<String>();
		list.add(user.getUsertype().code());
		return new UserCredentials(user.getEmail(), user.getPassword(), list);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials uc = getUserByUsername(username);
		List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<SimpleGrantedAuthority>();
		for (String role : uc.getRoles()) {
			grantedAuthority.add(new SimpleGrantedAuthority(role));
		}
		return new org.springframework.security.core.userdetails.User(uc.getEmail(), uc.getPassword(),
				grantedAuthority);
	}

}
