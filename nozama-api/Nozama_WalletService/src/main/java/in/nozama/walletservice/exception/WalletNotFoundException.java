package in.nozama.walletservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND, reason = "Wallet not found")
public class WalletNotFoundException extends Exception{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WalletNotFoundException(String message,Exception e) {
	        super(message, e);
	    }

	    public WalletNotFoundException(String message) {
	        super(message);
	    }

	
}
