package com.banco.banco_digital.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.banco.banco_digital.controller.form.UserForm;
import com.banco.banco_digital.model.User;
import com.banco.banco_digital.repository.UserRepository;



@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get()); 
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<User> post(@RequestBody @Valid  UserForm form, UriComponentsBuilder uriBuilder) {
		User user = form.convertUser();
		userRepository.save(user);
		
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<User> put(@PathVariable Long id, @RequestBody @Valid UserForm form) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User user = form.putUser(id, userRepository);
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
