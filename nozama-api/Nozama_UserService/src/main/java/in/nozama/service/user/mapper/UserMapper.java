package in.nozama.service.user.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import in.nozama.service.dto.AddressDTO;
import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.user.model.User;
import in.nozama.service.user.service.proxy.AddressServiceProxy;

@Component
public class UserMapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserMapper.class);

	public UserResponse map(User user) {
		UserResponse userResponse = null;
		if (user != null) {
			userResponse = new UserResponse();
			userResponse.setUserid(user.getUserid());
			userResponse.setEmail(user.getEmail());
			userResponse.setFullname(user.getFullname());
			userResponse.setUsertype(user.getUsertype());
			userResponse.setMobile(user.getMobile());
			userResponse.setGender(user.getGender());
		}
		return userResponse;
	}

	public List<UserResponse> map(List<User> users) {
		List<UserResponse> userResponse = new ArrayList<UserResponse>();

		if (users != null) {
			for (User user : users) {
				userResponse.add(map(user));
			}
		}
		return userResponse;
	}

	public List<UserResponse> map(List<User> users, AddressDTO address) {
		List<UserResponse> userResponse = new ArrayList<UserResponse>();

		if (users != null) {
			for (User user : users) {
				userResponse.add(map(user, address));
			}
		}
		return userResponse;
	}

	public Optional<UserResponse> map(Optional<User> user) {
		if (user.isPresent()) {
			return Optional.of(map(user.get()));
		} else {
			return null;
		}
	}

	public User map(CreateUserRequest userRequest) {
		User user = null;
		if (userRequest != null) {
			user = new User();
			user.setUserid(userRequest.getUserid());
			user.setEmail(userRequest.getEmail());
			user.setFullname(userRequest.getFullname());
			user.setGender(userRequest.getGender());
			user.setMobile(userRequest.getMobile());
			user.setPassword(userRequest.getPassword());
			user.setUsertype(userRequest.getUsertype());
		}
		return user;
	}

	public UserResponse map(User user, AddressDTO address) {
		UserResponse userResponse = null;
		if (user != null) {
			userResponse = new UserResponse();
			userResponse.setUserid(user.getUserid());
			userResponse.setEmail(user.getEmail());
			userResponse.setFullname(user.getFullname());
			userResponse.setUsertype(user.getUsertype());
			userResponse.setMobile(user.getMobile());
			userResponse.setGender(user.getGender());
			if (address != null) {
				userResponse.setAddress(address);
			}
		}
		return userResponse;
	}

	public List<UserResponse> map(List<User> users, AddressServiceProxy addressService) {
		if (addressService != null) {
			List<UserResponse> userResponse = new ArrayList<UserResponse>();

			if (users != null) {
				for (User user : users) {
					AddressDTO address = addressService.getAddressByUserId(user.getUserid());
					userResponse.add(map(user, address));
					LOG.error("Address Data" + userResponse.get(0).getAddress().toString());
				}
			}
			return userResponse;
		}
		return map(users);
	}

}
