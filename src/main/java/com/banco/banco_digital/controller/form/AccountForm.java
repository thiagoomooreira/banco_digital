package com.banco.banco_digital.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.banco.banco_digital.model.Account;
import com.banco.banco_digital.model.User;
import com.banco.banco_digital.repository.UserRepository;
import javax.validation.constraints.NotNull;

public class AccountForm {
	
	@NotNull @NotEmpty
	private String number;
	
	@NotNull @NotEmpty
	private String agency;
	
	private double purse;
	
	@NotNull
	private Long idUser;
	
	public Account convertAccount(UserRepository repository) {
		Optional<User> user = repository.findById(this.idUser);
		
		if(user.isPresent()) {
			return new Account(this.number, this.agency, user.get());
		}
		
		return null;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public double getPurse() {
		return purse;
	}

	public void setPurse(double purse) {
		this.purse = purse;
	}
	
}
