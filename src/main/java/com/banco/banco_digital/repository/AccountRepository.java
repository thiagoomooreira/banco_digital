package com.banco.banco_digital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco_digital.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByNumber(String number);

}
