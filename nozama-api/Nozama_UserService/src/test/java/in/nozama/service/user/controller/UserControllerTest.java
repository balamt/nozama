package in.nozama.service.user.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import in.nozama.service.dto.CreateUserRequest;
import in.nozama.service.dto.UserResponse;
import in.nozama.service.user.model.User;
import in.nozama.service.user.repository.UserRepository;
import in.nozama.service.user.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean 
	RestTemplate restTemplate;
	
	private CreateUserRequest userRequest;
	private 	Optional<UserResponse> userResponse;
	private Optional<User> user;
	private Long userId;
	
	@BeforeEach
	public void setUpMock() {
		userId = 1l;
		userRequest = new CreateUserRequest();
		userResponse = Optional.of(new UserResponse());
		user = Optional.of(new User());
	}
	
	
	@Test
	public void givenUserIdGetUserById() throws Exception {
		when(userService.getUserById(userId)).thenReturn(userResponse);
		
		RequestBuilder builder = MockMvcRequestBuilders.get(String.format("/user/%d",userId));
        MvcResult response = mockMvc.perform(builder).andReturn();
		
        LOGGER.info(response.getResponse().getContentAsString());
        verify(userService,times(1)).getUserById(userId);
	}
	
	

}
