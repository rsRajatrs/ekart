package com.ekartv2.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountDto {


	private Integer userId;

	@NotEmpty(message = "All fields are mandatory")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name contains invalid character")
	private String firstName;
	
	@NotEmpty(message = "All fields are mandatory")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name contains invalid character")
	private String lastName;
	
	@NotEmpty(message = "All fields are mandatory")
	@Email(message = "invalid email address")
	private String userEmail;
	
	@NotEmpty(message = "All fields are mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password should contain atleast an uppercase and a lower character, a number, and a special character")
	private String password;
	
	@NotEmpty(message = "All fields are mandatory")
	private String confirmPassword;
	
	@NotEmpty(message = "All fields are mandatory")
	private String accountType;
	
}
