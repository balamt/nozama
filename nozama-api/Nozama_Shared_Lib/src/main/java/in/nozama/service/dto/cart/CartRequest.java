package in.nozama.service.dto.cart;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long itemId;
	private Long userId;
	private Integer quantity = 0;
	private Long productId;
	private Double price = 0.0;

}
