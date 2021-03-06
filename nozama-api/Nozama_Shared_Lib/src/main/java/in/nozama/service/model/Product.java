package in.nozama.service.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product extends ResourceSupport implements Serializable {

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

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "thirdPartyUser")
	Warehouse warehouse;
	
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
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
