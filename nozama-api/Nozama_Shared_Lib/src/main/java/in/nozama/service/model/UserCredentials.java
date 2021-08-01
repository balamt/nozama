package in.nozama.service.model;

import java.io.Serializable;

public class UserCredentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginCredentials{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}