package in.nozama.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.view.UserModelView;
import lombok.Getter;
import lombok.Setter;

/*@Getter
@Setter*/
@JsonView(UserModelView.PublicView.class)
public class AddressDTO extends AddressRequest implements Serializable {

	private static final long serialVersionUID = 1L;
//	/**
//	 * 
//	 */
//	
//
//	@JsonProperty("addressid")
//	private Long addressId;
//
//	@Size(max = 100)
//	private String address1;
//
//	@Size(max = 100)
//	private String address2;
//
//	@Size(max = 100)
//	private String street;
//
//	@Size(max = 100)
//	private String city;
//
//	@Size(max = 100)
//	private String state;
//
//	@Size(max = 100)
//	private String country;
//
//	@JsonProperty("pincode")
//	@Size(max = 10)
//	private String pinCode;

}
