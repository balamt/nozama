package in.nozama.address.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.address.service.exception.AddressNotFoundException;
import in.nozama.address.service.service.AddressService;
import in.nozama.service.dto.AddressRequest;
import in.nozama.service.model.AddressResponse;
import in.nozama.service.model.ErrorResponse;
import in.nozama.service.model.Status;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	HttpHeaders headers;

	@GetMapping("/id/{id}")
	public ResponseEntity getAddressById(@PathVariable(value = "id") Long addressId) {
		if (addressId != null && addressId >= 1) {
			try {
				return new ResponseEntity<AddressResponse>(addressService.getAddressById(addressId), HttpStatus.OK);
			} catch (AddressNotFoundException e) {
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), Status.ERROR),
						HttpStatus.NOT_FOUND);
			}
		}
		ErrorResponse response = new ErrorResponse("Address Id Cannot be Empty!", Status.ERROR);
		if (addressId <= 0) {
			response.setMessage(String.format("Invalid Address Id %d", addressId));
		}
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity deleteAddressByAddressId(@PathVariable(value = "id") Long addressId) {
		addressService.removeAddressByAddressId(addressId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/userid/{userid}")
	public ResponseEntity deleteAddressByUserId(@PathVariable(value = "userid") Long userId) {
		addressService.removeAddressByUserId(userId);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/userid/{userid}")
	public ResponseEntity getAddressByUserId(@PathVariable(value = "userid") Long userid) {
		return new ResponseEntity(addressService.getAddressByUserId(userid), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addAddress(@RequestBody AddressRequest address) {
		Long addressid = -1l;
		ErrorResponse errorResponse = null;
		AddressResponse addressResponse = null;
		if (address != null) {
			addressResponse = addressService.save(address);
		}

		if (addressid <= -1l) {
			errorResponse = new ErrorResponse();
			errorResponse.setStatus(Status.ERROR);
			errorResponse.setMessage("Unable to store Address! Please try after sometime.");
		}
		return (addressid <= -1L) ? new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
	}

}
