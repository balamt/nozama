package in.nozama.nozamauserauthservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nozama.nozamauserauthservice.model.User;

@FeignClient(name = "nozama-userservice")
public interface UserServiceProxy {
	
	@RequestMapping("/user")
	public ResponseEntity<String> testServer();

	@RequestMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long userId);

	@RequestMapping("/user/signup")
	public ResponseEntity<User> addUser(@RequestBody User user);

}
