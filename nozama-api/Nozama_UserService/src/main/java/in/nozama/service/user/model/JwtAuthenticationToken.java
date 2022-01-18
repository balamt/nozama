package in.nozama.service.user.model;

import java.io.Serializable;


public class JwtAuthenticationToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;

	public JwtAuthenticationToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
