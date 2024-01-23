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
	private int id;
	private String nome;
	private int numeroMaglia;
	private int points;
	private String pos;
	private String min;
	private int fgm;
	private int fga;
	private double fgp;
	private int ftm;
	private int fta;
	private double ftp;
	private int tpm;
	private int tpa;
	private double tpp;
	private int offReb;
	private int defReb;
	private int totReb;
	private int assists;
	private int pFouls;
	private int steals;
	private int turnovers;
	private int blocks;
	private int plusMinus;
}
