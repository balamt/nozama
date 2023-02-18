package in.nozama.service.user.service.proxy;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.nozama.service.dto.AddressDTO;
import in.nozama.service.dto.AddressRequest;

@FeignClient(name = "nozama-addressservice", url = "${addressservice.ribbon.listOfServers}")
@LoadBalancerClient(name = "nozama-addressservice")
public interface AddressServiceProxy {
	
	@RequestMapping("/address/id/{id}")
	public ResponseEntity getAddressById(@PathVariable(value = "id") Long addressId);

	@RequestMapping(path = "/address/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addAddress(@RequestBody AddressRequest address);

	@RequestMapping(path = "/address/userid/{userid}")
	public AddressDTO getAddressByUserId(@PathVariable(value = "userid") Long userid);
	
	@RequestMapping(path = "/address/userid/{userid}", method = RequestMethod.DELETE)
	public AddressDTO deteleAddressByUserId(@PathVariable(value = "userid") Long userid);
	
}
