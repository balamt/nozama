package in.nozama.address.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.AddressResponse;
import in.nozama.address.service.service.AddressService;
import in.nozama.address.service.service.AddressServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class NozamaAddressServiceApplicationTests {

	@TestConfiguration
	static class AddressServiceContextConfiguration {

	}

	@Test
	public void contextLoads() {
	}

}
