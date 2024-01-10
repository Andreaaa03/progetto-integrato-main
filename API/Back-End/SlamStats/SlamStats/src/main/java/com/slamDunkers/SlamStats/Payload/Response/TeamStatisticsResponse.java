package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamStatisticsResponse {
	public TeamsResponse team;

	public Integer season;
	public Integer games;
	public Integer fastBreakPoints;
	public Integer pointsInPaint;
	public Integer biggestLead;
	public Integer secondChancePoints;
	public Integer pointsOffTurnover;
	public Integer points;
	public Integer fgm;
	public Integer fga;
	public Double fgp;
	public Integer ftm;
	public Integer fta;
	public Double ftp;
	public Integer tpm;
	public Integer tpa;
	public Double tpp;
	public Integer offReb;
	public Integer defReb;
	public Integer totReb;
	public Integer assists;
	public Integer steals;
	public Integer turnovers;
	public Integer blocks;
	public Integer plusMinus;
	public Integer pfouls;
	public Integer conferenceRank;
}
