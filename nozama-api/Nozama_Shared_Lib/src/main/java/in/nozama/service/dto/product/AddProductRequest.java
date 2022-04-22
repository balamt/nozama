package in.nozama.service.dto.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	String category;

	Integer stockQuantity;

	Double pricePerItem;

	Long warehouse;
	
	String productImg;
	
	List<String> tags;
	
	Long seller;
	
	Double rating;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date createdOn;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	Date updatedOn;

}
