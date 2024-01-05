package com.slamDunkers.SlamStats.Payload.Response;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.TeamsStatistics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DettaglioSquadraResponse {

	private TeamsStatistics statistics; //statistiche squadra & squadra
	private List<Player> players; //players della squadra
	private List<Games> Calendar; //calendario squadra






}
