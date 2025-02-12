package in.nozama.address.service.service;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.Address;
import in.nozama.address.service.model.AddressType;
import in.nozama.address.service.model.mapper.AddressMapper;
import in.nozama.address.service.repository.AddressRepository;
import in.nozama.service.model.AddressResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {
        AddressMapper.class,
        AddressServiceImpl.class
})
public class AddressServiceTest {

    @Mock
    AddressRepository addressRepository;

    @Mock
    AddressMapper addressMapper;

    @InjectMocks
    AddressServiceImpl addressService;

    private Long addressId, userId;
    private Address response;
    private AddressResponse addressResponse;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

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
        response.setPincode("909090");
        response.setUserId(userId);
        response.setAddressType(AddressType.HOME);

        addressResponse = new AddressResponse();
        addressResponse.setAddressId(addressId);
        addressResponse.setAddress1("address1");
        addressResponse.setAddress2("address2");
        addressResponse.setCity("city");
        addressResponse.setCountry("country");
        addressResponse.setStreet("street");
        addressResponse.setState("state");
        addressResponse.setPincode("909090");
        addressResponse.setUserId(userId);
        addressResponse.setAddressType(AddressType.HOME.toString());
    }

    @Test
    public void givenAddressIdWhenFindAddressByIdCalledVerifyAddressResponseReturned() throws AddressNotFoundException {
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(response));
        when(addressMapper.mapResponse(response)).thenReturn(addressResponse);
        AddressResponse response = addressService.getAddressById(addressId);
        assertNotNull(response);
    }

    @Test
    public void givenInvalidAddressIdWhenFindByIdCalledThrowAddressNotFoundException() throws AddressNotFoundException {
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());
        try {
            addressService.getAddressById(addressId);
        } catch (AddressNotFoundException e) {
            assertNotNull(e);
        }
    }
}
