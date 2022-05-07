package in.nozama.address.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.Address;
import in.nozama.address.service.model.mapper.AddressMapper;
import in.nozama.address.service.repository.AddressRepository;
import in.nozama.service.dto.AddressRequest;
import in.nozama.service.model.AddressResponse;

@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepository;

	@Autowired
	AddressMapper addressMapper;

	public AddressServiceImpl() {
	}

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
			throw new AddressNotFoundException(
					String.format("Address Not Found for the ID %d.", addressId));
		}
		return addressMapper.map(address);
	}

	@Override
	public AddressResponse save(AddressRequest addressRequest) {
		AddressResponse response = new AddressResponse();
		Address address = addressMapper.map(addressRequest);
		address = addressRepository.save(address);
		return addressMapper.map(address, response);
	}

	@Override
	public AddressResponse getAddressByUserId(Long userid) {
		return addressMapper.mapResponse(addressRepository.findByUserId(userid));
	}

}
