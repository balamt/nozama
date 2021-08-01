package in.nozama.nozamauserauthservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.core.userdetails.UserDetails;

import in.nozama.nozamauserauthservice.model.User;


@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByEmail(String username);

}
