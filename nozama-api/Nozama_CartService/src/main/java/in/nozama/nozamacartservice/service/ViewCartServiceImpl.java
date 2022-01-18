package in.nozama.nozamacartservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.nozamacartservice.model.Item;
import in.nozama.nozamacartservice.repository.CartRepository;

@Service
public class ViewCartServiceImpl implements ViewCartService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewCartServiceImpl.class);
	@Autowired
	CartRepository cartRepository;

	@Override
	public List<Item> viewCart(Long itemId) {
		LOGGER.info("Before viewing the item in the cart in ViewCartService");
		List<Item> viewCartresponse = cartRepository.findByItemId(itemId);
		LOGGER.info("In ViewCartService after fetching details from ViewCartRepository");
		return viewCartresponse;
	}

}
