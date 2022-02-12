package in.nozama.service.user.exception;

public class UserCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserCreationException(String message, Exception e) {
		super(message, e);
	}

}
