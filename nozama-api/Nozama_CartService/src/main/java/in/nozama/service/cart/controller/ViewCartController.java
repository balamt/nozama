package in.nozama.service.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.service.cart.model.Item;
import in.nozama.service.cart.service.ViewCartService;
import in.nozama.service.cart.util.CartResponse;

@RestController
@RequestMapping("/cart")
public class ViewCartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewCartController.class);
	
	@Autowired
	ViewCartService myCartService;
	


	@GetMapping(value = "/mycart/{itemId}")
	public ResponseEntity<CartResponse> viewCart(@PathVariable("itemId") Long itemId ){

		LOGGER.info("Before viewing the items in cart in ViewCartController ");
		List<Item> responseCartItem = myCartService.viewCart(itemId);

		if (responseCartItem.isEmpty()) {
			CartResponse cartresponse = new CartResponse();
			cartresponse.setItemlist(null);
			cartresponse.setMesssage("No Item Found in cart. Please Add");
			cartresponse.setStatus(true);
			LOGGER.info("No Item found in cart in ViewCartController ");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cartresponse);
		} else { 
			CartResponse cartresponse = new CartResponse();
			cartresponse.setItemlist(responseCartItem);
			cartresponse.setMesssage("succeffully fetched cart details");
			cartresponse.setStatus(true);

			LOGGER.info("After viewing the cart in ViewCartController ");
			return ResponseEntity.status(HttpStatus.OK).body(cartresponse);
		}

	}
}