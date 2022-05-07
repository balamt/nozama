package in.nozama.service.dto.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Category;
	String CategoryCode;

}
