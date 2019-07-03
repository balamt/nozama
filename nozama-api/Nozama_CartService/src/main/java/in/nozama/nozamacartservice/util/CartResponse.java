package in.nozama.nozamacartservice.util;

import java.util.List;

import in.nozama.service.model.Item;
import in.nozama.service.model.Product;

public class CartResponse {

	private List<Item> itemlist;
	private Item item;

	private Product product;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
