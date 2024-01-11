package com.slamDunkers.SlamStats.Entity;

import com.slamDunkers.SlamStats.Payload.Response.PlayerResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {
	@Id
	@Column(name = "ID", columnDefinition = "int")
	private int Id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id ", nullable = false)
	private Teams team;

	@Column(name = "first_name", columnDefinition = "varchar(255)")
	private String firstName;

	@Column(name = "last_name", columnDefinition = "varchar(255)")
	private String lastName;

	@Column(name = "birth_date", columnDefinition = "date", nullable = true)
	private LocalDate birthDate;

	@Column(name = "birth_Country", columnDefinition = "varchar(255)")
	private String birthCountry;

	@Column(name = "nba_Start", columnDefinition = "year")
	private Year nbaStart;

	@Column(name = "nba_pro", columnDefinition = "int")
	private Integer nbaPro;

	@Column(name = "height_feet", columnDefinition = "int")
	private Integer heightFeet;

	@Column(name = "height_inches", columnDefinition = "int")
	private Integer heightInches;

	@Column(name = "height_meters", columnDefinition = "decimal")
	private Double heightMeters;

	@Column(name = "weight_pounds", columnDefinition = "int")
	private Integer weightPounds;

	@Column(name = "weight_kg", columnDefinition = "decimal")
	private Double weightKilograms;

	@Column(name = "college", columnDefinition = "varchar(255)")
	private String college;

	@Column(name = "affiliation", columnDefinition = "varchar(255)")
	private String lastAffiliation;

//	public PlayerResponse toPlayerResponse() {
//		PlayerResponse playerResponse = new PlayerResponse();
//		playerResponse.playerId = this.Id;
//		playerResponse.team = this.team.toTeamsResponse();
//		playerResponse.firstName = this.firstName;
//		playerResponse.lastName = this.lastName;
//		playerResponse.birthDate = this.birthDate.toString();
//		playerResponse.birthCountry = this.birthCountry;
//		playerResponse.nbaStart = this.nbaStart.getValue();
//		playerResponse.nbaPro = this.nbaPro;
//		playerResponse.heightFeet = this.heightFeet;
//		playerResponse.heightInches = this.heightInches;
//		playerResponse.heightMeters = this.heightMeters;
//		playerResponse.weightPounds = this.weightPounds;
//		playerResponse.weightKg = this.weightKilograms;
//		playerResponse.college = this.college;
//		playerResponse.affiliation = this.lastAffiliation;
//		return playerResponse;
//	}
}
