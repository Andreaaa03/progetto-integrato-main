package com.slamDunkers.SlamStats.Payload.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamStandingsResponse {
	public TeamsResponse team;

	public Integer season;
	public Integer conferenceRank;
	public Integer conferenceWin;
	public Integer conferenceLoss;
	public double divisionGamesBehind;
	public Integer homeWin;
	public Integer awayWin;
	public Integer totalWin;
	public Double winPercentage;
	public Integer lastTenWin;
	public Integer homeLoss;
	public Integer awayLoss;
	public Integer totalLoss;
	public Integer lastTenLoss;
	public Double gamesBehind;



}
