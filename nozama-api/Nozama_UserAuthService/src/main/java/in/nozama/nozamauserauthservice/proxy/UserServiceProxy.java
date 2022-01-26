package in.nozama.nozamauserauthservice.proxy;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.nozama.nozamauserauthservice.model.User;
import in.nozama.service.model.UserCredentials;

@FeignClient(name = "nozama-userservice", url = "${userservice.ribbon.listOfServers}")
@LoadBalancerClient(name = "nozama-userservice")
public interface UserServiceProxy {
	
	@RequestMapping("/user")
	public ResponseEntity<String> testServer();

	@RequestMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long userId);

	@RequestMapping("/user/signup")
	public ResponseEntity<User> addUser(@RequestBody User user);
	
	@RequestMapping(path = "/user/email/{email}", method = RequestMethod.POST)
	public ResponseEntity<UserCredentials> getUserByUsername(@PathVariable(name="email") String email);

}
