package in.nozama.nozamauserauthservice.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.view.UserModelView;
import in.nozama.service.model.Gender;
import in.nozama.service.model.UserType;

@Entity
@Table(name = "users")
@JsonView(UserModelView.PublicView.class)
public class User extends RepresentationModel<User> implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userid")
	private Long userid;
	
	@Size(max = 120)
	@NotNull
	@Column(name="fullname")
	private String fullname;
	
	@Email
	@NotNull
	@Column(name="email", unique = true)
	private String email;

	@NotNull
	@Column(name = "mobile")
	private String mobile;
	
	@NotNull
	@Column(name = "password")
	@JsonView(UserModelView.ProtectedView.class)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "addressid")
	private Address address;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "usertype")
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
				Objects.equals(address, user.address) &&
				usertype == user.usertype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), userid, fullname, email, mobile, password, gender, address, usertype);
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
				", address=" + address +
				", usertype=" + usertype +
				'}';
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.usertype != null)
			return Arrays.asList(new SimpleGrantedAuthority(this.getUsertype().code()));
		else
			return Arrays.asList(new SimpleGrantedAuthority(UserType.BASIC.code()));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

