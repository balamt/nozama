package in.nozama.service.user.repository;

import in.nozama.service.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustomDao {

	boolean findUserByEmailAndPassword(String email, String password);

	User getUserByEmail(String email);
}
