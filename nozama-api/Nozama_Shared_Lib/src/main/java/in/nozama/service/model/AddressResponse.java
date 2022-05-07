package in.nozama.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.view.UserModelView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonView(UserModelView.PublicView.class)
public class AddressResponse implements Serializable {

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

	private String pincode;
	
	private String addressType;

}
