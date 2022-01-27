package in.nozama.service.product.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "products")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	Long productId;

	@Column(name = "product_code")
	@NotNull String productCode;

	@Column(name = "product_name")
	@NotNull String productName;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "category")
	Category category;

	@Column(name = "stock_quantity")
	Integer stockQuantity;

	@Column(name = "price_per_item")
	Double pricePerItem;

	@Column(name="warehouse_id")
	Long warehouse;
	
	String productImg;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Double getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(Double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public Long getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Long warehouse) {
		this.warehouse = warehouse;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productCode='" + productCode + '\'' +
				", productName='" + productName + '\'' +
				", category=" + category +
				", stockQuantity=" + stockQuantity +
				", pricePerItem=" + pricePerItem +
				", warehouse=" + warehouse +
				", productImg='" + productImg + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Product product = (Product) o;
		return productId.equals(product.productId) &&
				Objects.equals(productCode, product.productCode) &&
				Objects.equals(productName, product.productName) &&
				category == product.category &&
				Objects.equals(stockQuantity, product.stockQuantity) &&
				Objects.equals(pricePerItem, product.pricePerItem) &&
				Objects.equals(warehouse, product.warehouse) &&
				Objects.equals(productImg, product.productImg);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), productId, productCode, productName, category, stockQuantity, pricePerItem, warehouse, productImg);
	}
}
