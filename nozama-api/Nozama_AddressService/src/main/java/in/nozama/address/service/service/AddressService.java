package in.nozama.address.service.service;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.AddressResponse;
import in.nozama.service.dto.AddressRequest;

public interface AddressService {

	AddressResponse getAddressById(long addressId) throws AddressNotFoundException;

	Long save(AddressRequest addressRequest);

	AddressResponse getAddressByUserId(Long userid);

}
