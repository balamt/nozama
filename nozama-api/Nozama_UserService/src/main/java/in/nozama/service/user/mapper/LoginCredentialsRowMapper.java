package in.nozama.service.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import in.nozama.service.user.model.UserCredentials;

@Component
public class LoginCredentialsRowMapper implements RowMapper<UserCredentials> {

	@Override
	public UserCredentials mapRow(ResultSet row, int rowNum) throws SQLException {

		UserCredentials credentials = new UserCredentials();

		credentials.setEmail(row.getString("email"));
		credentials.setPassword(row.getString("password"));
		return credentials;
	}
}
