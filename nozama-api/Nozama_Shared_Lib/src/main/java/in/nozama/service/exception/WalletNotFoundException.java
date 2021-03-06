package in.nozama.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND, reason = "Wallet not found")
public class WalletNotFoundException extends Exception{

	 public WalletNotFoundException(String message,Exception e) {
	        super(message, e);
	    }

	    public WalletNotFoundException(String message) {
	        super(message);
	    }

	
}
