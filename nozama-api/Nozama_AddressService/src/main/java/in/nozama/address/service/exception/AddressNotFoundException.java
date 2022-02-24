package in.nozama.address.service.exception;

public class AddressNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddressNotFoundException() {
		super();
	}

	public AddressNotFoundException(String message) {
		super(message);
	}

	public AddressNotFoundException(String message, Throwable thr) {
		super(message, thr);
	}

}
