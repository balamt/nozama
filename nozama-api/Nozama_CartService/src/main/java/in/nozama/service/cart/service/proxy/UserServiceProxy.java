package in.nozama.service.cart.service.proxy;

import org.hibernate.service.spi.ServiceException;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "nozama-userservice", url = "${userservice.ribbon.listOfServers}")
@LoadBalancerClient(name = "nozama-userservice")
public interface UserServiceProxy {
	@RequestMapping(path = "/user/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<Long> getUserIdByUsername(@PathVariable(name="email") String email) throws ServiceException;
}
