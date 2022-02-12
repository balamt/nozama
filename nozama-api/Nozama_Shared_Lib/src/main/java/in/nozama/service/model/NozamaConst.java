package in.nozama.service.model;

import java.io.Serializable;

public class NozamaConst implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NozamaConst() {}
	
	public static final String NOZAMA = "nozama";
	public static final String APP_NAME = NOZAMA;
	public static final String APP_BASE_URL = "https://localhost:8080"; 
	public static final String FORGOT_PASSWORD_URL = String.format("%s/forgotpassword", APP_BASE_URL);
	

}
