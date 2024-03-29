package in.nozama.service.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import in.nozama.service.cart.mapper.CartMapper;
import in.nozama.service.cart.model.Item;
import in.nozama.service.cart.service.AddCartService;
import in.nozama.service.dto.cart.CartResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cart")
public class AddCartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddCartController.class);

	@Autowired
	AddCartService addCartService;

	@Autowired
	CartMapper cartMapper;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/add")
	public ResponseEntity<CartResponse> addCart(@RequestBody Item item) {
		CartResponse cartReponse = new CartResponse();
		Long productId = item.getProduct();
		Long product = -1L;
		System.out.println(productId);
		Long[] productDetails = restTemplate.getForObject("https://apigw.nozama.in:8090/product/" , Long[].class);
		System.out.println(productDetails.toString());
		for (Long products : productDetails) {
			if (products.equals(productId)) {
				product = products;
			}
		}

		if (product <= -1L) {
			cartReponse.setMesssage("PRODUCT DOES NOT EXISTS");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cartReponse);
		}

		item.setProduct(product);
		LOGGER.info("after calling rest template");

		LOGGER.info("Before Adding item into the cart in AddCartController");

		Item responseItem = addCartService.addCart(item);
		System.out.println(responseItem.toString());
		if (responseItem.getItemPrice() != null) {
			cartReponse = cartMapper.map(responseItem);
			cartReponse.setStatus(true);
			cartReponse.setMesssage("Successfully product added into cart");

			LOGGER.info("After Adding item into the cart in AddCartController");

		} else {
			cartReponse = cartMapper.map(responseItem);
			cartReponse.setStatus(false);
			cartReponse.setMesssage("OUT OF STOCK");

			LOGGER.info("Adding item into the cart in AddCartController is failed due to OUT OF STOCK");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cartReponse);
	}
}
