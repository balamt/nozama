package in.nozama.address.service.model.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import in.nozama.address.service.model.Address;
import in.nozama.address.service.model.AddressResponse;
import in.nozama.address.service.model.AddressType;
import in.nozama.service.dto.AddressRequest;

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

	public AddressResponse mapResponse(Address from) {
		if (from != null) {
			return map(Optional.of(from));
		} else {
			return null;
		}
	}

	public Address map(AddressRequest from) {
		Address to = new Address();
		if (from != null) {
			to.setAddress1(from.getAddress1());
			to.setAddress2(from.getAddress2());
			if (from.getAddressType() != null) {
				to.setAddressType(AddressType.valueOf(from.getAddressType()));
			}
			to.setCity(from.getCity());
			to.setCountry(from.getCountry());
			to.setState(from.getState());
			to.setStreet(from.getStreet());
			to.setPinCode(from.getPinCode());
			to.setUserId(from.getUserId());
		}
		return to;
	}

	public AddressRequest map(Address from) {
		AddressRequest to = new AddressRequest();
		if (from != null) {
			to.setAddress1(from.getAddress1());
			to.setAddress2(from.getAddress2());
			to.setAddressType(from.getAddressType().getValue());
			to.setCity(from.getCity());
			to.setCountry(from.getCountry());
			to.setState(from.getState());
			to.setStreet(from.getStreet());
			to.setPinCode(from.getPinCode());
			to.setUserId(from.getUserId());
		}
		return to;
	}

	public AddressRequest map(AddressResponse from) {
		AddressRequest to = new AddressRequest();
		if (from != null) {
			to.setAddress1(from.getAddress1());
			to.setAddress2(from.getAddress2());
			to.setAddressType(from.getAddressType());
			to.setCity(from.getCity());
			to.setCountry(from.getCountry());
			to.setState(from.getState());
			to.setStreet(from.getStreet());
			to.setPinCode(from.getPinCode());
			to.setUserId(from.getUserId());
		}
		return to;
	}

}
