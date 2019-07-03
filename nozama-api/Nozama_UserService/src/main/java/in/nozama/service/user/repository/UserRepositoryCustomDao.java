package in.nozama.service.user.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustomDao {

	boolean findUserByEmailAndPassword(String email, String password);
}
