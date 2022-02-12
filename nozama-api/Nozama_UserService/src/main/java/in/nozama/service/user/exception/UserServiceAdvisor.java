package in.nozama.service.user.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class UserServiceAdvisor extends ResponseEntityExceptionHandler {
	
	public static final String MESSAGE = "message";
	public static final String TIMESTAMP = "timestamp";
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	public ResponseEntity<Object> handleUserCreationException(UserAlreadyExistsException uae, WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, uae.getMessage());
		return new ResponseEntity<Object>(body, HttpStatus.NOT_IMPLEMENTED);
	}

}
