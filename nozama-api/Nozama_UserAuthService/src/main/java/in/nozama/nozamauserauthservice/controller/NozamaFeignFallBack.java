package in.nozama.nozamauserauthservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import in.nozama.nozamauserauthservice.exception.ServiceException;
import in.nozama.nozamauserauthservice.model.User;
import in.nozama.nozamauserauthservice.proxy.UserServiceProxy;
import in.nozama.service.model.UserCredentials;

@Component
public class NozamaFeignFallBack implements UserServiceProxy {

	@Override
	public ResponseEntity<String> testServer() {
		return new ResponseEntity<String>("Service is Down", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<User> getUserById(Long userId) throws ServiceException {
		throw new ServiceException("User Service is taking longer time to respond.");
	}

	@Override
	public ResponseEntity<User> addUser(User user) throws ServiceException {
		throw new ServiceException("User Service is taking longer time to respond.");
	}

	@Override
	public ResponseEntity<UserCredentials> getUserByUsername(String email) throws ServiceException {
		throw new ServiceException("User Service is taking longer time to respond.");
	}

}
