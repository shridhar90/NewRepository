package com.Banking.Banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Banking.Banking.entity.AccountDetails;
import com.Banking.Banking.entity.CustomerDetails;
import com.Banking.Banking.entity.FundTransferRequest;
import com.Banking.Banking.service.BankingService;

@RestController
@RequestMapping("api")
public class Bankingcontroller {

	@Autowired
	private BankingService bankingService;
	
	
	@PostMapping("/product/createaccount")
	public ResponseEntity<AccountDetails> createAccount(@RequestBody CustomerDetails customerDetails) {
		if (customerDetails.getAge() > 18) {
		AccountDetails accountDetails = bankingService.createBankingUser(customerDetails);
		return new ResponseEntity<>(accountDetails, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(new AccountDetails(), HttpStatus.BAD_REQUEST);	
		}
	}
	
	@PostMapping("/product/transferamount")
	public ResponseEntity<FundTransferRequest> transferAmount(@RequestBody FundTransferRequest fundTransferRequest) {
		FundTransferRequest request = bankingService.transferMoney(fundTransferRequest);
		if (request != null) {
		return new ResponseEntity<>(request,HttpStatus.OK);
		}
		else {
			request = new FundTransferRequest();
			request.setType("Not Successful due to insufficient funds");
			return new ResponseEntity<>(request, HttpStatus.BAD_REQUEST);	
		}
	}
	
	@GetMapping("/accounthistory/{accountnumber}")
	public ResponseEntity<List<FundTransferRequest>> accountHistory(@PathVariable Long accountnumber) {
		List<FundTransferRequest> accountHistory  = bankingService.getAccountHistory(accountnumber);
		return new ResponseEntity<>(accountHistory,HttpStatus.OK);
	}
	
}
