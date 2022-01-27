package in.nozama.walletservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.walletservice.exception.WalletNotFoundException;
import in.nozama.walletservice.model.Wallet;
import in.nozama.walletservice.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepository walletRepository;

	Wallet wallet = new Wallet();

	@Override
	public Wallet checkAmount(String mobileNo) {
		wallet.setUpdatedDate(LocalDateTime.now());
		walletRepository.save(wallet);
		return walletRepository.findByMobileNo(mobileNo);

	}

	@Override
	public Wallet addAmount(String mobileNo, Double amount) {
		wallet.setAmount(amount);
		wallet.setMobileNo(mobileNo);
		wallet.setUpdatedDate(LocalDateTime.now());
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet deductAmount(String mobileNo, Double amount) throws WalletNotFoundException {
		Wallet walletobj = walletRepository.findByMobileNo(mobileNo);

		if (walletobj != null) {

			if (walletobj.getAmount() > amount) {
				wallet.setUpdatedDate(LocalDateTime.now());
				walletRepository.save(wallet);
				double DeductedAmount = walletobj.getAmount() - amount;
				walletobj.setAmount(DeductedAmount);
				return walletRepository.save(walletobj);
			} else {
				throw new WalletNotFoundException("Insufficient funds in wallet");
			}
		} else {
			throw new WalletNotFoundException("No Such Mobile No linked to Wallet");
		}
	}

}
