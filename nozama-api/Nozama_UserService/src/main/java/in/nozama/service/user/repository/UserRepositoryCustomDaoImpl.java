package in.nozama.service.user.repository;


import in.nozama.service.user.mapper.UsersRowMapper;
import in.nozama.service.user.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomDaoImpl implements UserRepositoryCustomDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryCustomDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean findUserByEmailAndPassword(String email, String password) {
		User user = this.getUserByEmail(email);
		boolean isPasswordMatching = false;
		try {
			if(user!=null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				isPasswordMatching = passwordEncoder.matches(password, user.getPassword());
			}
		} catch (Exception exp) {
			LOGGER.error("UserRepositoryCustomDaoImpl.findUserByEmailAndPassword " + exp.getMessage());
		}
		return isPasswordMatching;
	}

	@Override
	public User getUserByEmail(String email) {
		String SQLQuery = "SELECT * FROM users where email = '"+ email+"'";
		User user = null;
		RowMapper<User> userRowMapper = new UsersRowMapper();
		try {
			user = jdbcTemplate.queryForObject(SQLQuery, userRowMapper);

		} catch (Exception exp) {
			LOGGER.error("UserRepositoryCustomDaoImpl.getUserByEmail " + exp.getMessage());
		}
		return user;
	}

}
