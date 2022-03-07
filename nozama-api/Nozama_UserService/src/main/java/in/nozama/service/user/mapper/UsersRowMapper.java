package in.nozama.service.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import in.nozama.service.model.Gender;
import in.nozama.service.model.UserType;
import in.nozama.service.user.model.User;

@Component
public class UsersRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setEmail(row.getString("email"));
        user.setUserid(row.getLong("userid"));
        user.setFullname(row.getString("fullname"));
        user.setGender(Gender.valueOf(row.getString("gender")));
        user.setMobile(row.getString("mobile"));
        user.setUsertype(UserType.valueOf(row.getString("usertype")));
        user.setPassword(row.getString("password"));
        return user;
    }
}
