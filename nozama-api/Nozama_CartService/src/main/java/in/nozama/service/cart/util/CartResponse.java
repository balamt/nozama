package in.nozama.service.cart.util;

import java.util.List;

import in.nozama.service.cart.model.Item;

public class CartResponse {

	private List<Item> itemlist;
	private Item item;

	private Long product;
	private String messsage;
	private boolean status;

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Item> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

}
