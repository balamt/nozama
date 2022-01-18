package in.nozama.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND, reason = "No Such Order found")
public class OrderNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message,Exception e) {
        super(message, e);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

}
