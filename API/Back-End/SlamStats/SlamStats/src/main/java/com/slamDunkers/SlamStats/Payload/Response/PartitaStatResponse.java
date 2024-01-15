package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartitaStatResponse {
	public CalendarioDateResponse calendarioDateResponse;

	public TeamStatGameResponse homeTeam;
	public TeamStatGameResponse awayTeam;





}
