package in.nozama.walletservice.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nozama.walletservice.exception.WalletNotFoundException;
import in.nozama.walletservice.model.Wallet;
import in.nozama.walletservice.service.WalletService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/wallet")
public class WalletController {

	@Autowired
	WalletService walletService;

	@PostMapping("/add/{mobile}/{amount}")
	public ResponseEntity<Wallet> addAmount(@PathVariable(name = "mobile") String mobileNo,
			@PathVariable(name = "amount") Double Amount) {
		Wallet walletDetails = walletService.addAmount(mobileNo, Amount);
		return ResponseEntity.ok(walletDetails);
	}

	@GetMapping("/check/{mobile}")
	public ResponseEntity<Wallet> checkAmount(@PathVariable(name = "mobile") String mobileNo) {
		Wallet walletDetails = walletService.checkAmount(mobileNo);
		return ResponseEntity.ok(walletDetails);
	}

	@PostMapping("/deduct/{mobile}/{amount}")
	public ResponseEntity<Wallet> deductAmount(@PathVariable(name = "mobile") String mobileNo,
			@PathVariable(name = "amount") Double Amount) throws WalletNotFoundException {
		Wallet walletDetails = walletService.deductAmount(mobileNo, Amount);
		return ResponseEntity.ok(walletDetails);
	}

	@ExceptionHandler
	void WalletNotFoundExceptionHandler(WalletNotFoundException wnfe, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), wnfe.getMessage());
	}
}
