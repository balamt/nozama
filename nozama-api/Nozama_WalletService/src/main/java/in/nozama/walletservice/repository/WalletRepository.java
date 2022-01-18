package in.nozama.walletservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nozama.walletservice.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

	Wallet findByMobileNo(String mobileNo);

}
