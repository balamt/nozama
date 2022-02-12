package in.nozama.service.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_STATUS = "ERROR";
	
	private String status;
	private String message;
	private boolean isError;
	private LocalDateTime date;
	

}
