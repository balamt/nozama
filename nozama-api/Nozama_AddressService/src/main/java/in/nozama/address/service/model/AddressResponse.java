package in.nozama.address.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.view.UserModelView;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
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

	private String pinCode;
	
	private String addressType;

}
