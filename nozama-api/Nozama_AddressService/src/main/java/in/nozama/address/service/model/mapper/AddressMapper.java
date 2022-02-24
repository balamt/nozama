package in.nozama.address.service.model.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.Address;
import in.nozama.address.service.model.AddressResponse;

@Component
public class AddressMapper {

	public AddressResponse map(Optional<Address> from) {
		AddressResponse to = new AddressResponse();
		if (from.isPresent()) {			
			Address address = from.get();
			to.setAddressId(address.getAddressId());
			to.setAddress1(address.getAddress1());
			to.setAddress2(address.getAddress2());
			to.setAddressType(address.getAddressType().toString());
			to.setCity(address.getCity());
			to.setCountry(address.getCountry());
			to.setPinCode(address.getPinCode());
			to.setUserId(address.getUserId());
			to.setState(address.getState());
			to.setStreet(address.getStreet());
		}
		return to;
	}

}
