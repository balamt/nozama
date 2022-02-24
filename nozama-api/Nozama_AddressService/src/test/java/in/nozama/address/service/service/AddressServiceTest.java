package in.nozama.address.service.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.Address;
import in.nozama.address.service.model.AddressResponse;
import in.nozama.address.service.model.AddressType;
import in.nozama.address.service.model.mapper.AddressMapper;
import in.nozama.address.service.repository.AddressRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {
		AddressMapper.class,
		AddressServiceImpl.class
})
public class AddressServiceTest {
	
	@Mock
	AddressRepository addressRepository;

	AddressService addressService;
	
	@Mock
	AddressMapper addressMapper;

	private Long addressId, userId;
	private Address response;
	private AddressResponse addressResponse;

	@Before
	public void setup() {
		addressRepository = Mockito.mock(AddressRepository.class);
		addressMapper = Mockito.mock(AddressMapper.class);
		
		userId = 123L;
		addressId = 1L;
		response = new Address();
		response.setAddressId(addressId);
		response.setAddress1("address1");
		response.setAddress2("address2");
		response.setCity("city");
		response.setCountry("country");
		response.setStreet("street");
		response.setState("state");
		response.setPinCode("909090");
		response.setUserId(userId);
		response.setAddressType(AddressType.HOME);
	}

	@Test
	public void givenAddressIdWhenFindAddressByIdCalledVerifyAddressReponseReturned() throws AddressNotFoundException {
		when(addressRepository.findById(addressId)).thenReturn(Optional.of(response));
		when(addressMapper.map(Optional.of(response))).thenReturn(addressResponse);
		addressService = new AddressServiceImpl(addressRepository);
		AddressResponse response = addressService.getAddressById(addressId);
		assertNotNull(response);
	}
	
	@Test
	public void givenInvalidAddressIdWhenFindByIdCalledThrowAddressNotFoundException() throws AddressNotFoundException {
		when(addressRepository.findById(addressId)).thenThrow(AddressNotFoundException.class);
		when(addressMapper.map(Optional.of(response))).thenReturn(addressResponse);
		addressService = new AddressServiceImpl(addressRepository);
		AddressResponse response = addressService.getAddressById(addressId);
		assertNotNull(response);
	}

}
