package com.ekartv2.service;

import com.ekartv2.payload.AccountDto;
import com.ekartv2.payload.AccountLoginDto;
import com.ekartv2.payload.AccountUpdateDto;

public interface AccountService {

	public String registerUser(AccountDto accountDto);
	
	public AccountLoginDto userLogin(AccountLoginDto accountloginDto);
	
	public String updateUserCredentials(AccountUpdateDto accountDto);
	
	
}
