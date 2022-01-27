package in.nozama.service.user.repository;

import org.springframework.stereotype.Repository;

import in.nozama.service.user.model.User;

@Repository
public interface UserRepositoryCustomDao {

	boolean findUserByEmailAndPassword(String email, String password);

	User getUserByEmail(String email);
}
