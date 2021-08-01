package in.nozama.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND, reason = "Tracking or Shipment Id not found")
public class ShipmentNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShipmentNotFoundException(String message){
        super(message);
    }

    public ShipmentNotFoundException(String message, Exception e){
        super(message,e );
    }

}
