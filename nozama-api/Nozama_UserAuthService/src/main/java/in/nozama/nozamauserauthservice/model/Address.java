package in.nozama.nozamauserauthservice.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="addressid")
	private Long addressId;

	@Size(max = 100)
	private String address1;

	@Size(max = 100)
	private String address2;

	@Size(max = 100)
	private String street;

	@Size(max = 100)
	private String city;

	@Size(max = 100)
	private String state;

	@Size(max = 100)
	private String country;

	@Column(name = "pincode")
	@Size(max = 10)
	private String pincode;

	

	@Override
	public String toString() {
		return "Address{" +
				"addressId=" + addressId +
				", address1='" + address1 + '\'' +
				", address2='" + address2 + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", country='" + country + '\'' +
				", pinCode='" + pincode + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return addressId.equals(address.addressId) &&
				Objects.equals(address1, address.address1) &&
				Objects.equals(address2, address.address2) &&
				Objects.equals(street, address.street) &&
				Objects.equals(city, address.city) &&
				Objects.equals(state, address.state) &&
				Objects.equals(country, address.country) &&
				Objects.equals(pincode, address.pincode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId, address1, address2, street, city, state, country, pincode);
	}
}
