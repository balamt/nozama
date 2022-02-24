package in.nozama.address.service.service;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.AddressResponse;

public interface AddressService {

	AddressResponse getAddressById(long addressId) throws AddressNotFoundException;

}
