package in.nozama.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, value = HttpStatus.INTERNAL_SERVER_ERROR,
        reason = "Unable to create User")
public class UserNotCreatedError extends Throwable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotCreatedError(String message){
        super(message);
    }

    public UserNotCreatedError(String message, Exception e){
        super(message,e );
    }

}
