package com.ekartv2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekartv2.entity.Account;
import com.ekartv2.exceptions.ErrorMessageException;
import com.ekartv2.payload.AccountDto;
import com.ekartv2.payload.AccountLoginDto;
import com.ekartv2.payload.AccountUpdateDto;
import com.ekartv2.repository.AccountRepo;
import com.ekartv2.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;
	
	static String userSession;
	
	@Override
	public String registerUser(AccountDto accountDto) {

		Account existingAccount = accountRepo.findByUserEmail(accountDto.getUserEmail());
		
		if(existingAccount != null)
		{
			throw new ErrorMessageException("email ID already used!!!");
		}
		
		if(!accountDto.getPassword().equals(accountDto.getConfirmPassword()))
		{
			throw new ErrorMessageException("Password & Confirm Password are not matching!!!");
		}
		
		Account account = dtoToAccount(accountDto);
		accountRepo.save(account);
		
		return account.getUserEmail()+" is registered succesfully!!!";
	}
	
	@Override
	public AccountLoginDto userLogin(AccountLoginDto accountLoginDto) {
		
		Account account = accountRepo.findByUserEmail(accountLoginDto.getUserEmail());
		
		if(account==null)
		{
			throw new ErrorMessageException(accountLoginDto.getUserEmail() + " does not exists!!! Please register");
		}
		else if(!account.getPassword().equals(accountLoginDto.getPassword()))
		{
			throw new ErrorMessageException("Wrong Password");
		}
		
		userSession = accountLoginDto.getUserEmail();
		
		AccountLoginDto accountLoginDto1 = new AccountLoginDto();
		accountLoginDto1.setFirstName(account.getFirstName());
		accountLoginDto1.setLastName(account.getLastName());
		accountLoginDto1.setUserId(account.getUserId());
		accountLoginDto1.setUserEmail(account.getUserEmail());
		accountLoginDto1.setAccountType(account.getAccountType());
		
		return accountLoginDto1;
	}

	@Override
	public String updateUserCredentials(AccountUpdateDto accountDto) {
		
		if(userSession == null)
		{
			throw new ErrorMessageException("Login to udpate your credentials");
		}
		
		Account account = accountRepo.findByUserEmail(userSession);
		
		account.setFirstName(accountDto.getUpdateFirstName());
		account.setLastName(accountDto.getUpdateLastName());
		account.setPassword(accountDto.getUpdatePassword());
		
		accountRepo.save(account);
		
		return "Credentials updated successfully!!!";
	}
	
	private static Account dtoToAccount(AccountDto accountDto)
	{
		Account account = new Account();
		account.setFirstName(accountDto.getFirstName());
		account.setLastName(accountDto.getLastName());
		account.setUserEmail(accountDto.getUserEmail());
		account.setPassword(accountDto.getPassword());
		account.setAccountType(accountDto.getAccountType());
		return account;
	}
	
	private static AccountDto accountToDto(Account account)
	{
		AccountDto accountDto = new AccountDto();
		accountDto.setUserId(account.getUserId());
		accountDto.setFirstName(account.getFirstName());
		accountDto.setLastName(account.getLastName());
		accountDto.setUserEmail(account.getUserEmail());
		return accountDto;
	}

	
}
