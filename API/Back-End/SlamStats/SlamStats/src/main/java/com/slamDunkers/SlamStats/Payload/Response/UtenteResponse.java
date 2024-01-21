package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtenteResponse {

	private int id;
	private String email;
	private String numeroTelefono;
	private String username;
	private String sesso;
	private int follower;
	private int favoriteTeam;

}
