package in.nozama.address.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="addressid")
	private Long addressId;
	
	@Column(name="userid", nullable = false)
	@NotNull
	private Long userId;

	@Size(max = 200)
	private String address1;

	@Size(max = 200)
	private String address2;

	@Size(max = 100)
	private String street;

	@Size(max = 100)
	private String city;

	@Size(max = 100)
	private String state;

	@Size(max = 50)
	private String country;

	@Column(name = "pincode")
	@Size(max = 10)
	private String pinCode;
	
	@Column(name="addresstype")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

}
