package com.ekartv2.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountUpdateDto {

	@NotEmpty(message = "All fields are mandatory")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name contains invalid character")
	private String updateFirstName;
	
	@NotEmpty(message = "All fields are mandatory")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name contains invalid character")
	private String updateLastName;
	
	@NotEmpty(message = "All fields are mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password should contain atleast an uppercase and a lower character, a number, and a special character")
	private String updatePassword;
	
}
