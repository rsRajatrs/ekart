package com.ekartv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekartv2.payload.AccountDto;
import com.ekartv2.payload.AccountLoginDto;
import com.ekartv2.payload.AccountUpdateDto;
import com.ekartv2.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<String> registerNewUser(@Valid @RequestBody AccountDto accountDto)
	{
		return new ResponseEntity<>(accountService.registerUser(accountDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/login")
	public ResponseEntity<AccountLoginDto> userLogin(@RequestBody AccountLoginDto accountLoginDto)
	{
		return new ResponseEntity<>(accountService.userLogin(accountLoginDto), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateUserCredentials(@Valid @RequestBody AccountUpdateDto updateDto)
	{
		return ResponseEntity.ok(accountService.updateUserCredentials(updateDto));
	}
}
