package in.nozama.walletservice.service;

import in.nozama.walletservice.exception.WalletNotFoundException;
import in.nozama.walletservice.model.Wallet;

public interface WalletService {

	Wallet checkAmount(String mobileNo);

	Wallet addAmount(String mobileNo, Double amount);

	Wallet deductAmount(String mobileNo, Double amount) throws WalletNotFoundException;

}
