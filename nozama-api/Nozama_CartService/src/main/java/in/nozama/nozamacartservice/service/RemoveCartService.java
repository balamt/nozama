package in.nozama.nozamacartservice.service;

import java.util.List;

import in.nozama.nozamacartservice.model.Item;

public interface RemoveCartService {

	List<Item> removeItemFromCart(Long itemId);

}
