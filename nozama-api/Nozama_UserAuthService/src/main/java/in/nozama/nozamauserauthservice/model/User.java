package in.nozama.nozamauserauthservice.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "users")
@JsonView(UserModelView.PublicView.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "addressid")
	private Long addressid;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "usertype")
	private UserType usertype;

	
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
				Objects.equals(addressid, user.addressid) &&
				usertype == user.usertype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), userid, fullname, email, mobile, password, gender, addressid, usertype);
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
				", address=" + addressid +
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

