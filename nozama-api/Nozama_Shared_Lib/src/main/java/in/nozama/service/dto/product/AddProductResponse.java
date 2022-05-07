package in.nozama.service.dto.product;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductResponse extends AddProductRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long productId;

}
