package in.nozama.address.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AddressType {
	
	WAREHOUSE("WareHouse"),
	HOME("Home"),
	OFFICE("Office"),
	SHOP("Shop"),
	VENDOR("Vendor");
	
	
	@Getter 
	private String value;

}
