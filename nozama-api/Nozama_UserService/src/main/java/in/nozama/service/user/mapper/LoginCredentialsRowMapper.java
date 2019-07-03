package in.nozama.service.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import in.nozama.service.model.*;


public class LoginCredentialsRowMapper implements RowMapper<LoginCredentials> {

	@Override
	public LoginCredentials mapRow(ResultSet row, int rowNum) throws SQLException {

		LoginCredentials credentials = new LoginCredentials();

		credentials.setEmail(row.getString("email"));
		credentials.setPassword(row.getString("password"));
		return credentials;
	}
}
