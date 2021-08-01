package in.nozama.service.user.mapper;

import in.nozama.service.model.Gender;
import in.nozama.service.model.User;
import in.nozama.service.model.UserType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UsersRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setEmail(row.getString("email"));
        user.setUserid(row.getLong("user_id"));
        user.setFullname(row.getString("fullname"));
        user.setGender(Gender.valueOf(row.getString("gender")));
        user.setMobile(row.getString("mobile"));
        user.setUsertype(UserType.valueOf(row.getString("user_type")));
        user.setPassword(row.getString("password"));
        user.setAddress(null);
        return user;
    }
}
