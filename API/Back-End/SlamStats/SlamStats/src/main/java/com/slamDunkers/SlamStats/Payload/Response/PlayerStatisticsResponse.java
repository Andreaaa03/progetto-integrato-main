package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatisticsResponse {
	public int id;
	public String nome;
	public int points;
	public String pos;
	public String min;
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



}
