package in.nozama.Nozama_WalletService.service;

import in.nozama.service.exception.WalletNotFoundException;
import in.nozama.service.model.Wallet;

public interface WalletService {

	Wallet checkAmount(String mobileNo);

	Wallet addAmount(String mobileNo, Double amount);

	Wallet deductAmount(String mobileNo, Double amount) throws WalletNotFoundException;

}
