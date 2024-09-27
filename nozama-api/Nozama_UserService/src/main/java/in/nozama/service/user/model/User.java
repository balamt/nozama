package in.nozama.service.user.model;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import in.nozama.service.dto.view.UserModelView;
import in.nozama.service.model.Gender;
import in.nozama.service.model.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@SequenceGenerator(name="USERSEQ", sequenceName = "USERSEQ", initialValue = 30000, allocationSize = 2)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	
	@JoinColumn(name = "addressid")
	private Long addressid;
	
}

