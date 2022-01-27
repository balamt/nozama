package in.nozama.nozamacartservice.service;

import java.util.List;

import in.nozama.nozamacartservice.model.Item;

public interface ViewCartService {

	List<Item> viewCart(Long itemId);

}
