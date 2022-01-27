package in.nozama.nozamacartservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nozama.nozamacartservice.model.Item;
import in.nozama.nozamacartservice.repository.CartRepository;

@Service
public class RemoveCartServiecImpl implements RemoveCartService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveCartServiecImpl.class);

	@Autowired
	CartRepository cartRepository;

	@Transactional
	@Override
	public List<Item> removeItemFromCart(Long itemId) {
		LOGGER.info("before removing item from cart in RemoveCartService");
		List<Item> removeItem = cartRepository.deleteItemByItemId(itemId);
		LOGGER.info("In RemoveCartService after fetching details from RemoveCartRepository");
		return removeItem;
	}

}
