package com.slamDunkers.SlamStats.Payload.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamStatGameResponse {
	public int fgm;
	public int fga;
	public double fgp;
	public int ftm;
	public int fta;
	public double ftp;
	public int tpm;
	public int tpa;
	public double tpp;
	public int offReb;
	public int defReb;
	public int totReb;
	public int assists;
	public int pFouls;
	public int steals;
	public int turnovers;
	public int blocks;
	public int plusMinus;
	public List<PlayerStatisticsResponse> playersStatistics;
}
