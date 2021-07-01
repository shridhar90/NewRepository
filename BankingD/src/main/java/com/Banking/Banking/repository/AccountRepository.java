package com.Banking.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.Banking.entity.AccountDetails;
import com.Banking.Banking.entity.CustomerDetails;

public interface AccountRepository extends JpaRepository<AccountDetails, Long> {
	AccountDetails findByAccountNumberEquals(Long fromAccountNumber);
}
