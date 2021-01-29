package com.banco.banco_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco_digital.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
