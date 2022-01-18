package in.nozama.service.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import in.nozama.service.model.Gender;
import in.nozama.service.model.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("user_id")
	private Long userid;
	
	@Size(max = 120)
	@NotNull
	@JsonProperty("fullname")
	private String fullname;
	
	@Email
	@NotNull
	@JsonProperty("email")
	private String email;

	@NotNull
	@JsonProperty("mobile")
	private String mobile;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("gender")
	private Gender gender;
	
	@JsonProperty("address")
	private AddressDTO address;

	@Enumerated(EnumType.STRING)
	@NotNull
	@JsonProperty("user_type")
	private UserType usertype;

}
