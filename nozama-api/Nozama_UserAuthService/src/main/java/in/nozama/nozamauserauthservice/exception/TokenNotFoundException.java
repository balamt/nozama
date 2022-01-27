package in.nozama.nozamauserauthservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND, reason = "No Token found in the request")
public class TokenNotFoundException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenNotFoundException() {
		super();
	}

	public TokenNotFoundException(String message) {
		super(message);
	}

	public TokenNotFoundException(Throwable cause) {
		super(cause);
	}

	public TokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
