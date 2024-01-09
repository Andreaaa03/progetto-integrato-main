package com.slamDunkers.SlamStats.Payload.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;

import java.util.Date;

@Getter
public class SignupRequest {
	@NotBlank
	@Size(min=4)
	private String first_name;

	@NotBlank
	@Size(min=4)
	private String last_name;


	private Date birth_date;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min=6)
	private String pswd;
}
