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
public class PlayerResponse {
	public Integer playerId;

	public TeamsResponse team;
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
	public int numeroMaglia;
	public List<PlayerStatisticsResponse> statistics;




}
