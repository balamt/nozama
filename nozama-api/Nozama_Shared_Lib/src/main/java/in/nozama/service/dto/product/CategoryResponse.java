package in.nozama.service.dto.product;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> categories;
	private long size;

}
