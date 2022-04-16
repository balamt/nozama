package in.nozama.service.product.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull String productCode;

	@NotNull String productName;
	
	@NotNull String productDescription;

	@Enumerated(EnumType.STRING)
	Category category;

	Integer stockQuantity;

	Double pricePerItem;

	Long warehouse;
	
	String productImg;
	
	List<String> tags;
	
	Long seller;
	
	Double rating;

}
