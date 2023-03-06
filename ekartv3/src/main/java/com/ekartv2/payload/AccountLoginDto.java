package com.ekartv2.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountLoginDto {

	private String userEmail;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Integer userId;
	
	private String accountType;
	
}
