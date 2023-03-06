package com.ekartv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekartv2.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{

	public Account findByUserEmail(String userEmail);
	
}
