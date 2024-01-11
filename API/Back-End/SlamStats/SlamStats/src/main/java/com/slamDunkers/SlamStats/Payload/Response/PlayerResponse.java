package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
	public TeamsResponse team;
	public Integer playerId;
	public String firstName;
	public String lastName;
	public String birthDate;
	public int age;
	public String birthCountry;
	public Integer nbaStart;
	public Integer nbaPro;
	public Integer heightFeet;
	public Integer heightInches;
	public Double heightMeters;
	public Integer weightPounds;
	public Double weightKg;
	public String college;
	public String affiliation;

	public int points;
	public String posizione;
	public int assists;

//	public String min;
//	public int fgm;
//	public int fga;
//	public double fgp;
//	public int ftm;
//	public int fta;
//	public double ftp;
//	public int tpm;
//	public int tpa;
//	public double tpp;
//	public int offReb;
//	public int defReb;
//	public int totReb;
//	public int pFouls;
//	public int steals;
//	public int turnovers;
//	public int blocks;
//	public int plusMinus;


}
