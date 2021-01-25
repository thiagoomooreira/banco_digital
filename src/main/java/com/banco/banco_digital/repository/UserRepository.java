package com.banco.banco_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco_digital.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
