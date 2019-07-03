package in.nozama.service.user.repository;


import in.nozama.service.model.LoginCredentials;
import in.nozama.service.user.mapper.LoginCredentialsRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustomDaoImpl implements UserRepositoryCustomDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryCustomDaoImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Override
		public boolean findUserByEmailAndPassword(String email, String password) {
			String SQLQuery = "SELECT * FROM users where email = '"+ email+"' and password = '"+password+"'";
			List<LoginCredentials> resultList = null;
			RowMapper<LoginCredentials> credentialsRowMapper = new LoginCredentialsRowMapper();

			try {
				resultList = jdbcTemplate.query(SQLQuery, credentialsRowMapper);

			} catch (Exception exp) {
				LOGGER.error(exp.getMessage());
				
			}
			return (resultList!=null && resultList.isEmpty());
	}

}
