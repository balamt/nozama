package in.nozama.service.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse implements Serializable {

	public ErrorResponse(String message, Status status) {
		this.message = message;
		this.status = status;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private Status status;
	private boolean isError = true;
}
