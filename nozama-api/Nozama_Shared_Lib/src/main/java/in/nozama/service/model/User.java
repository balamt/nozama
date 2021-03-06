package in.nozama.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User extends ResourceSupport implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long userid;
	
	@Size(max = 120)
	@NotNull
	@Column(name="fullname")
	private String fullname;
	
	@Email
	@NotNull
	@Column(name="email")
	private String email;

	@NotNull
	@Column(name = "mobile")
	private String mobile;
	
	@NotNull
	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@JsonIgnore
	@Transient
	@OneToMany
	private List<Order> orders;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "user_type")
	private UserType usertype;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		User user = (User) o;
		return userid.equals(user.userid) &&
				Objects.equals(fullname, user.fullname) &&
				Objects.equals(email, user.email) &&
				Objects.equals(mobile, user.mobile) &&
				Objects.equals(password, user.password) &&
				gender == user.gender &&
				Objects.equals(orders, user.orders) &&
				Objects.equals(address, user.address) &&
				usertype == user.usertype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), userid, fullname, email, mobile, password, gender, orders, address, usertype);
	}

	@Override
	public String toString() {
		return "User{" +
				"userid=" + userid +
				", fullname='" + fullname + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", password=" + password +
				", gender=" + gender +
				", orders=" + orders +
				", address=" + address +
				", usertype=" + usertype +
				'}';
	}
}

