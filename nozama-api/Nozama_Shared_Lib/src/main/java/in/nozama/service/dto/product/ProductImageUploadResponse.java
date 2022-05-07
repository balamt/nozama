package in.nozama.service.dto.product;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageUploadResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long productId;
	private String status = "SUCCESS";
	private boolean isError = false;

}
