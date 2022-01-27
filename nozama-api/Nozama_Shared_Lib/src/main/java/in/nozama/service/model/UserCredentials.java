package in.nozama.service.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private List<String> roles; 
	
	
	public UserCredentials() {}
	
	public UserCredentials(String email, String password, List<String> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
				
	}

	@Override
	public String toString() {
		return "LoginCredentials{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
