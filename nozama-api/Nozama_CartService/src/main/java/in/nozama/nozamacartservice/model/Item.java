package in.nozama.nozamacartservice.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	@Column(name = "quantity")
	private Integer quantity = 0;

	@Column(name = "product_id")
	private Long product;

	@Column(name = "price")
	private Double itemPrice = 0.0;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "Item{" + "itemId=" + itemId + ", stockQuantity=" + quantity + ", product=" + product + ", itemPrice="
				+ itemPrice + '}';
	}
}
