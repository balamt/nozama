package in.nozama.address.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.Address;
import in.nozama.address.service.model.AddressResponse;
import in.nozama.address.service.model.mapper.AddressMapper;
import in.nozama.address.service.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepository;

	@Autowired
	AddressMapper addressMapper;
	
	public AddressServiceImpl() {}

	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Autowired
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public AddressResponse getAddressById(long addressId) throws AddressNotFoundException {
		Optional<Address> address = addressRepository.findById(addressId);
		if (address.isEmpty()) {
			throw new AddressNotFoundException("Address Not Found.");
		}
		return addressMapper.map(address);
	}

}
