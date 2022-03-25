package in.nozama.service.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.service.cart.model.Item;
import in.nozama.service.cart.service.RemoveCartService;
import in.nozama.service.cart.util.CartResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cart")
public class RemoveCartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveCartController.class);

	@Autowired
	RemoveCartService removeCartService;

	@DeleteMapping(value = "/remove/{itemId}")

	public ResponseEntity<CartResponse> removeItemFromCart(@PathVariable("itemId") Long itemId) {

		LOGGER.info("before removing item from cart in RemoveCartController");
		List<Item> removeItem = removeCartService.removeItemFromCart(itemId);

		if (removeItem.isEmpty()) {
			CartResponse cartResponse = new CartResponse();
			cartResponse.setItemlist(null);
			cartResponse.setMesssage("No Item found in cart to remove. Please Add Any Item");
			cartResponse.setStatus(true);
			LOGGER.info("No item found in cart in RemoveCartController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cartResponse);

		} else {
			CartResponse cartResponse = new CartResponse();
			cartResponse.setItemlist(removeItem);
			cartResponse.setMesssage("Item removed from the Cart Successfully");
			cartResponse.setStatus(true);
			LOGGER.info("after removing item from cart in RemoveCartController");
			return ResponseEntity.status(HttpStatus.OK).body(cartResponse);

		}
	}
}
