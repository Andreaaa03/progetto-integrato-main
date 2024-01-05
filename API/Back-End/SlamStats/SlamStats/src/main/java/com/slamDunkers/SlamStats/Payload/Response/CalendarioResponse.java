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
public class CalendarioResponse {

	private List<Integer> id; //id partita
	private List<String> startDate; //data inizio
	private String teamHomeName; //squadra casa
	private List<String> teamAwayName; //squadra ospite

	private List<Integer> scoreTeamHome; //punteggio squadra casa
	private List<Integer> scoreTeamAway; //punteggio squadra ospite
	private String conferenceName; //conference
	private String divisionName; //division
	private String teamName; //nome squadra
	private Integer teamId;
	private boolean allStar;
	private String nickname;
	private String logo;
	private String city;
	private String code;


}
