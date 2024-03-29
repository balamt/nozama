package in.nozama.service.shipment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT, reason = "Shipment already created")
public class ShipmentAlreadyCreatedException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShipmentAlreadyCreatedException(String message) {
        super(message);
    }
}
