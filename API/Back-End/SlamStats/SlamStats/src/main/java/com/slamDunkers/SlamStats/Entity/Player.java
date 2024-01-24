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
	@Column(name = "numero_maglia", columnDefinition = "int")
	private Integer numeroMaglia;
	
	@Column(name = "foto1", columnDefinition = "varchar(255)")
	private String foto1;
	@Column(name = "foto2", columnDefinition = "varchar(255)")
	private String foto2;
	@Column(name = "foto3", columnDefinition = "varchar(255)")
	private String foto3;
}
