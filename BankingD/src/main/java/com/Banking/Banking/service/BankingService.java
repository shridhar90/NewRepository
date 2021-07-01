package com.Banking.Banking.service;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Banking.Banking.entity.AccountDetails;
import com.Banking.Banking.entity.CustomerDetails;
import com.Banking.Banking.entity.FundTransferRequest;
import com.Banking.Banking.repository.AccountRepository;
import com.Banking.Banking.repository.BankingRepository;
import com.Banking.Banking.repository.TransactionRepository;




@Service
public class BankingService {
	@Autowired
	private BankingRepository bankingRepository;

	@Autowired
	private AccountRepository accountRepositoryDto;

	@Autowired
	private TransactionRepository transactionRepositoryDto;
	static Logger logger = Logger.getLogger(BankingService.class.getName());

	public AccountDetails createBankingUser(CustomerDetails customerDetails) {
		CustomerDetails c = bankingRepository.save(customerDetails);
		logger.log(Level.INFO, () ->  "c created: {0} " + c);
		AccountDetails account = createAccount(c.getCustomerId());
		return account;

	}

	public AccountDetails createAccount(Long customerId) {
		
		AccountDetails accountDetails = new AccountDetails("Savings", "Home", customerId, "BANK001", 500.00d);
		AccountDetails a = accountRepositoryDto.save(accountDetails);
		//Optional<AccountDetails> a=accountRepositoryDto.save(accountDetails);
		logger.log(Level.INFO, () ->  "a created: {0} " + a);
		return a;
	}

	public FundTransferRequest transferMoney(FundTransferRequest fundTransferRequest) {
		Long fromAccountNumber = fundTransferRequest.getFromAccount();
		Long toAccountNumber = fundTransferRequest.getToAccount();
		Double amount = fundTransferRequest.getAmount();
		AccountDetails fromAccount = accountRepositoryDto.findByAccountNumberEquals(fromAccountNumber);
		AccountDetails toAccount = accountRepositoryDto.findByAccountNumberEquals(toAccountNumber);
		if (fromAccount.getOpening_balance().compareTo(1.0d) >= 1
				&& fromAccount.getOpening_balance().compareTo(amount) >= 1) {
			Double balance = fromAccount.getOpening_balance() - amount;
			fromAccount.setOpening_balance(balance);
			AccountDetails fromAccountDetails = accountRepositoryDto.save(fromAccount);
			logger.log(Level.INFO, () ->  "fromAccountDetails: {0} " + fromAccountDetails);
			Double toBalance = toAccount.getOpening_balance() + amount;
			toAccount.setOpening_balance(toBalance);
			AccountDetails toAccountDetails = accountRepositoryDto.save(toAccount);
			logger.log(Level.INFO, () ->  "toAccountDetails: {0} " + toAccountDetails);
			Timestamp datetime = new Timestamp(System.currentTimeMillis());
			FundTransferRequest request = transactionRepositoryDto.save(new FundTransferRequest(amount,
					fromAccountNumber, toAccountNumber, datetime, fundTransferRequest.getType()));
			return request;
		}
		return null;
	}
	private FundTransferRequest deductMoneyFromAccount(FundTransferRequest fundTransferRequest) {
		Long fromAccountNumber = fundTransferRequest.getFromAccount();
		Double amount = fundTransferRequest.getAmount();
		AccountDetails fromAccount = accountRepositoryDto.findByAccountNumberEquals(fromAccountNumber);
		if (null != fromAccount && fromAccount.getOpening_balance().compareTo(1.0d) >= 1
				&& fromAccount.getOpening_balance().compareTo(amount) >= 1) {
			Double balance = fromAccount.getOpening_balance() - amount;
			fromAccount.setOpening_balance(balance);
			AccountDetails fromAccountDetails = accountRepositoryDto.save(fromAccount);
			logger.log(Level.INFO, () ->  "fromAccountDetails: {0} " + fromAccountDetails);
			Timestamp datetime = new Timestamp(System.currentTimeMillis());
			FundTransferRequest request = transactionRepositoryDto.save(
					new FundTransferRequest(amount, fromAccountNumber, null, datetime, fundTransferRequest.getType()));
			return request;
		}
		return null;
	}

	public List<FundTransferRequest> getAccountHistory(Long accountNumber) {
		return transactionRepositoryDto.findByFromAccountEquals(accountNumber);
	}
}
