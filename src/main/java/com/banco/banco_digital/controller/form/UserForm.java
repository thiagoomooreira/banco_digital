package com.banco.banco_digital.controller.form;

import javax.validation.constraints.NotEmpty;

import com.banco.banco_digital.model.User;
import com.banco.banco_digital.repository.UserRepository;
import com.sun.istack.NotNull;

public class UserForm {
	
	@NotNull @NotEmpty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User convertUser() {
		return new User(name);
	}
	
	public User putUser(Long id, UserRepository repository) {
		User user = repository.getOne(id);
		
		user.setName(this.name);
		
		return user;
	}
}
