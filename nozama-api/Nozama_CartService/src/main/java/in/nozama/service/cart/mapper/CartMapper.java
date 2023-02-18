package in.nozama.service.cart.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import in.nozama.service.cart.model.Item;
import in.nozama.service.dto.cart.CartRequest;
import in.nozama.service.dto.cart.CartResponse;

@Component
public class CartMapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(CartMapper.class);

	public CartResponse map(Item from) {
		CartResponse response = new CartResponse();
		List<CartRequest> items = new ArrayList();
		CartRequest item = new CartRequest();
		item.setItemId(from.getItemId());
		item.setPrice(from.getItemPrice());
		item.setProductId(from.getProduct());
		item.setQuantity(from.getQuantity());
		item.setUserId(from.getUserId());
		items.add(item);
		response.setItemlist(items);
		return response;
	}
	
	public CartRequest mapRequest(Item from) {
		CartRequest item = new CartRequest();
		item.setItemId(from.getItemId());
		item.setPrice(from.getItemPrice());
		item.setProductId(from.getProduct());
		item.setQuantity(from.getQuantity());
		item.setUserId(from.getUserId());
		return item;
	}

	public CartResponse map(List<Item> from) {
		List<CartRequest> req = new ArrayList();
		for(Item i : from) {
			req.add(mapRequest(i));
		}
		CartResponse response = new CartResponse();
		response.setItemlist(req);
		return response;
	}
	
	

}
