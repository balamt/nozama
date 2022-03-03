package in.nozama.service.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	private Long userId;
	private String address1;
	private String address2;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	private String addressType;

}
