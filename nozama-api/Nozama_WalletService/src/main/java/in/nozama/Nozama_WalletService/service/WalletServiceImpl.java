package in.nozama.Nozama_WalletService.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nozama.Nozama_WalletService.service.WalletService;
import in.nozama.Nozama_WalletService.repository.WalletRepository;
import in.nozama.service.exception.WalletNotFoundException;
import in.nozama.service.model.Wallet;

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
