package in.nozama.nozamacartservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.nozamacartservice.repository.CartRepository;
import in.nozama.service.model.Item;

@Service
public class AddCartServiceImpl implements AddCartService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddCartServiceImpl.class);

	@Autowired
	CartRepository cartRepository;

	@Override
	public Item addCart(Item item) {

		LOGGER.info("Before adding item into the cart in AddCartService");

		if (item.getQuantity() <= item.getProduct().getStockQuantity()) {

			double totalPrice = item.getQuantity() * item.getProduct().getPricePerItem();
			item.setItemPrice(totalPrice);

			LOGGER.info("After adding item into the cart in AddCartService");
			return cartRepository.save(item);
		} else {
			LOGGER.info("Afterstock is empty in AddCartService");

			item.setItemPrice(null);
			return item;
		}
	}

}
