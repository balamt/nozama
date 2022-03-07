package in.nozama.address.service.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nozama.address.service.NozamaAddressServiceApplicationTests;
import in.nozama.address.service.model.AddressResponse;
import in.nozama.address.service.model.AddressType;
import in.nozama.address.service.model.mapper.AddressMapper;
import in.nozama.address.service.service.AddressService;
import in.nozama.service.dto.AddressRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AddressControllerTest extends NozamaAddressServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@MockBean
	private AddressService addressService;

	AddressResponse response;

	AddressRequest request;

	Long addressId, userId;

	AddressMapper mapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		response = new AddressResponse();
		userId = 123L;
		addressId = 1L;

		response.setAddressId(addressId);
		response.setAddress1("address1");
		response.setAddress2("address2");
		response.setCity("city");
		response.setCountry("country");
		response.setStreet("street");
		response.setState("state");
		response.setPinCode("909090");
		response.setUserId(userId);
		response.setAddressType(AddressType.HOME.toString());

		mapper = new AddressMapper();
		request = mapper.map(response);
	}

	@Test
	public void giveAddressControllerGetByIdIsCalledConfirmWeGetOkStatus() throws Exception {
		when(addressService.getAddressById(addressId)).thenReturn(response);
		ResponseEntity<AddressResponse> respEntity = restTemplate.getForEntity("/address/id/" + addressId,
				AddressResponse.class);
		assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void givenWhenAddressIdIsGivenGetAddressResponse() throws Exception {
		when(addressService.getAddressById(addressId)).thenReturn(response);
		mockMvc.perform(get("/address/id/{id}", addressId)).andDo(print()).andExpect(jsonPath("$.userId").value(userId))
				.andExpect(jsonPath("$.address1").value("address1"))
				.andExpect(jsonPath("$.addressType").value(AddressType.HOME.toString()))
				.andExpect(jsonPath("$.addressId").value(addressId));
	}

	@Test
	public void givenInvalidAddressIdWhenGetAddressByIdCalledGetErrorResponseWithMessageInvalidAddressId()
			throws Exception {
		long invalidAddressId = -1L;
		mockMvc.perform(get("/address/id/{id}", invalidAddressId)).andDo(print())
				.andExpect(jsonPath("$.message").value(String.format("Invalid Address Id %d", invalidAddressId)));
	}

	@Test
	public void givenNullAddressIdWhenGetAddressByIdIsCalledGetErrorResponseWithMessageInvalidAddressId()
			throws Exception {
		Long invalidAddressId = (Long) null;
		mockMvc.perform(get("/address/id/{id}", invalidAddressId)).andDo(print())
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}

	@Test
	public void givenAddressRequestWhenAddAddressIsCalledSaveAddress() throws Exception {
		mockMvc.perform(post("/address/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request)))
				.andDo(print()).andExpect(status().isCreated());

	}
	
	@Test
	public void givenAddressRequestWhenAddAddressIsCalledSaveAddressFailed() throws Exception {
		mockMvc.perform(post("/address/add").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());

	}

	private String asJsonString(AddressRequest obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
