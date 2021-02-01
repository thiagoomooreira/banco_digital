package com.banco.banco_digital.controller.form;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferForm {
	@NotNull
	private AccountForm accountOrigin;
	@NotNull
	private AccountForm	accountDestiny;
	
	@Min(1)
	private double value;
	
	
	public AccountForm getAccountOrigin() {
		return accountOrigin;
	}
	public void setAccountOrigin(AccountForm accountOrigin) {
		this.accountOrigin = accountOrigin;
	}
	public AccountForm getAccountDestiny() {
		return accountDestiny;
	}
	public void setAccountDestiny(AccountForm accountDestiny) {
		this.accountDestiny = accountDestiny;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double valor) {
		this.value = valor;
	}
	
	
	
}
