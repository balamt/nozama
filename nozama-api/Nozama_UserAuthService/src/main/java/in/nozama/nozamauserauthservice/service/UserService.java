package in.nozama.nozamauserauthservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.nozama.nozamauserauthservice.model.User;

@Service
public interface UserService {
	
	List<User> getUsers();

}
