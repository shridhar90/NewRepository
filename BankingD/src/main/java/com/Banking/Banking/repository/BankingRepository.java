package com.Banking.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.Banking.entity.CustomerDetails;

public interface BankingRepository extends JpaRepository<CustomerDetails, Long> {

}
