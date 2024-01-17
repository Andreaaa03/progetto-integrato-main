package com.slamDunkers.SlamStats.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SinginRequest {
	@NotBlank
	private String email;

	@NotBlank @Size(min=6)
	private String passwd;
}
