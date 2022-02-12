package in.nozama.service.cart.service;

import java.util.List;

import in.nozama.service.cart.model.Item;

public interface RemoveCartService {

	List<Item> removeItemFromCart(Long itemId);

}
