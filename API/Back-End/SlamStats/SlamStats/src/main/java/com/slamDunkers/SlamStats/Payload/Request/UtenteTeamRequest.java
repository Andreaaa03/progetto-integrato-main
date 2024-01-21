package com.slamDunkers.SlamStats.Payload.Request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtenteTeamRequest {
	private String token;
	private Integer idTeam;

}
