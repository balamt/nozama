package in.nozama.address.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.model.AddressResponse;
import in.nozama.address.service.service.AddressService;
import in.nozama.service.model.ErrorResponse;
import in.nozama.service.model.Status;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping("/id/{id}")
	public ResponseEntity getAddressById(@PathVariable(value = "id") Long addressId) throws AddressNotFoundException {
		if (addressId != null && addressId >= 1) {
			return new ResponseEntity<AddressResponse>(addressService.getAddressById(addressId), HttpStatus.OK);
		}
		ErrorResponse response = new ErrorResponse("Address Id Cannot be Empty!", Status.ERROR);
		if (addressId <= 0) {
			response.setMessage(String.format("Invalid Address Id %d", addressId));
		}
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

}
