package in.nozama.service.dto.cart;

import java.util.List;

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
public class CartResponse {

	private List<CartRequest> itemlist;
	private String messsage;
	private boolean status;

}
