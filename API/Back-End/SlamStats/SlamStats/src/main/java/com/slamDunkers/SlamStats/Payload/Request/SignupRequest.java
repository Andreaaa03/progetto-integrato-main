package com.slamDunkers.SlamStats.Payload.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
	@NotBlank
	@Size(min=4)
	private String first_name;

	@NotBlank
	@Size(min=4)
	private String last_name;


	private LocalDate birthDate;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min=6)
	private String passwd;

	@NotBlank
	@Size(min=10)
	private String numeroTelefono;

	@NotBlank
	@Size(min=4)
	private String username;

	@NotBlank
	@Size(min=1)
	private String sesso;

}
