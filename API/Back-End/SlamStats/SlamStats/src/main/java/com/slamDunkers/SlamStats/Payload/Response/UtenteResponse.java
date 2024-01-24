package com.slamDunkers.SlamStats.Payload.Response;

import com.slamDunkers.SlamStats.Entity.Teams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
	private int eta;
	private LocalDate dataIscrizione;
	private String ruolo;

}
