package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioDateResponse {

	private Integer gameid; //id partita
	private String gameStartDate; //data inizio
	private String teamHomeName; //squadra casa
	private String teamAwayName; //squadra ospite

	private Integer scoreTeamHome; //punteggio squadra casa
	private Integer scoreTeamAway; //punteggio squadra ospite
	private String conferenceNameHome; //conference
	private String conferenceNameAway; //conference
	private String divisionNameHome; //division
	private String divisionNameAway; //division
	private Integer teamIdHome; //nome squadra
	private Integer teamIdAway; //nome squadra
	private boolean allStarHome;
	private boolean allStarAway;
	private String nicknameHome;
	private String nicknameAway;
	private String logoHome;
	private String logoAway;
	private String cityHome;
	private String cityAway;
	private String codeHome;
	private String codeAway;



}
