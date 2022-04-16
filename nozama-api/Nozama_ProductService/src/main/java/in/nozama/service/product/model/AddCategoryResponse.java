package in.nozama.service.product.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryResponse extends AddCategoryRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Status;
	long categoryId;
}
