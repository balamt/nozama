package in.nozama.service.user.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.user.model.User;

@Component
public class UserMapper {
	
	public UserResponse map(User user) {
		UserResponse userResponse = null;
		if(user != null) {
			userResponse = new UserResponse();
			userResponse.setUserid(user.getUserid());
			userResponse.setEmail(user.getEmail());
			userResponse.setFullname(user.getFullname());
			userResponse.setUsertype(user.getUsertype());
			userResponse.setMobile(user.getMobile());
			userResponse.setGender(user.getGender());
			userResponse.setAddress(null); // TODO: Need to write mapper for address			
		}
		return userResponse;
	}
	
	public List<UserResponse> map(List<User> users) {
		List<UserResponse> userResponse = new ArrayList<UserResponse>();
		if(users != null) {
			for(User user : users) {
				userResponse.add(map(user));
			}
		}
		return userResponse;
	}

	public Optional<UserResponse> map(Optional<User> user) {
		return map(user);
	}
	
	public User map(CreateUserRequest userRequest) {
		User user = null;
		if(userRequest!= null) {
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

}
