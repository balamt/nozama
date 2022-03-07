package in.nozama.address.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nozama.address.service.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByUserId(long userid);

}
