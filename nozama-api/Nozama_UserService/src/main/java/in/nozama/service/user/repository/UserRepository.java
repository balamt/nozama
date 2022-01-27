package in.nozama.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import in.nozama.service.user.model.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

}
