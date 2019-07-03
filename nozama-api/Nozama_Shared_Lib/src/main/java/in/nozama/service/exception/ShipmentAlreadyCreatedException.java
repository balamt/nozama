package in.nozama.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT, reason = "Shipment already created")
public class ShipmentAlreadyCreatedException extends Exception {
    public ShipmentAlreadyCreatedException(String message) {
        super(message);
    }
}
