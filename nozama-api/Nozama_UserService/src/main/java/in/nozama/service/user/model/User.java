package in.nozama.service.user.model;

import java.io.Serializable;

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

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.view.UserModelView;
import in.nozama.service.model.Gender;
import in.nozama.service.model.UserType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@JsonView(UserModelView.PublicView.class)
@Getter
@Setter
public class User extends RepresentationModel<User> implements Serializable {
	
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

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "usertype")
	private UserType usertype;

	public UserType getUsertype() {
		if(usertype == null) {
			return UserType.BASIC;
		}
		return usertype;
	}
	
}

