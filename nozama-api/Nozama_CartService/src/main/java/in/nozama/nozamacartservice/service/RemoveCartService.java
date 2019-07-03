package in.nozama.nozamacartservice.service;

import java.util.List;

import in.nozama.service.model.Item;

public interface RemoveCartService {

	List<Item> removeItemFromCart(Long itemId);

}
