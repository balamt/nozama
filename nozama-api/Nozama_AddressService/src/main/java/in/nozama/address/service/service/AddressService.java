package in.nozama.address.service.service;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.service.dto.AddressRequest;
import in.nozama.service.model.AddressResponse;

public interface AddressService {

	AddressResponse getAddressById(long addressId) throws AddressNotFoundException;

	AddressResponse save(AddressRequest addressRequest);

	AddressResponse getAddressByUserId(Long userid);

	void removeAddressByAddressId(Long addressId);

	void removeAddressByUserId(Long userId);

}
