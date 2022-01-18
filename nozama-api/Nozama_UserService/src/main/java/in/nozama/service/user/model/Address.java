package in.nozama.service.user.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "addresses")
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
	private String pinCode;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

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
				", pinCode='" + pinCode + '\'' +
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
				Objects.equals(pinCode, address.pinCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId, address1, address2, street, city, state, country, pinCode);
	}
}
