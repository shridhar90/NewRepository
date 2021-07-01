package com.Banking.Banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.Banking.entity.FundTransferRequest;

public interface TransactionRepository extends JpaRepository<FundTransferRequest, Long> {
	List<FundTransferRequest> findByFromAccountEquals(Long fromAccount);
}
