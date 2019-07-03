package in.nozama.Nozama_WalletService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nozama.service.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

	Wallet findByMobileNo(String mobileNo);

}
