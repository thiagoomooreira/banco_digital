package com.banco.banco_digital.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.banco.banco_digital.controller.form.AccountForm;
import com.banco.banco_digital.controller.form.TransferForm;
import com.banco.banco_digital.model.Account;
import com.banco.banco_digital.repository.AccountRepository;
import com.banco.banco_digital.repository.UserRepository;


@RestController
@RequestMapping("/accounts")
public class AccountsController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping
	public List<Account> getAll() {
		List<Account> accounts = accountRepository.findAll();
		return accounts;
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<AccountForm> openAccount(@RequestBody @Valid AccountForm form, UriComponentsBuilder uriBuilder) {
		Account account = form.convertAccount(userRepository);
		
		if(account != null) {
			accountRepository.save(account);
			
			URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(account.getId()).toUri();
			return ResponseEntity.created(uri).body(form);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}/{value}")
	@Transactional
	public ResponseEntity<?> insertPurse(@PathVariable Long id, @PathVariable double value) {
		Optional<Account> accountOptional = accountRepository.findById(id);
		
		
		if(accountOptional.isPresent()) {
			Account account = accountOptional.get();
			account.setPurse(value);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/transfer")
	@Transactional
	public ResponseEntity<?> transferPurse(@RequestBody @Valid TransferForm form) {
		Optional<Account> accountOriginOptional = accountRepository.findByNumber(form.getAccountOrigin().getNumber());
		Optional<Account> accountDestinyOptional = accountRepository.findByNumber(form.getAccountDestiny().getNumber());
		
		if(accountOriginOptional.isPresent() && accountDestinyOptional.isPresent()) {
			boolean status = accountOriginOptional.get().transferPurse(accountDestinyOptional.get(), form.getValue());
			
			if(status)
				return ResponseEntity.ok().build();
			
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
