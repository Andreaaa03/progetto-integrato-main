package com.slamDunkers.SlamStats.Entity;

import com.slamDunkers.SlamStats.Payload.Response.CalendarioResponse;
import com.slamDunkers.SlamStats.Repository.ScoreRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Games {

	@Id
	@Column(name = "ID", columnDefinition = "int")
	private int Id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "season_ID", nullable = false)
	private Season season;




	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "home_team")
	private Teams homeTeam;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "away_team")
	private Teams awayTeam;

	@Column(name = "start_date", columnDefinition = "datetime")
	private String startDate;
	@Column(name = "end_date", columnDefinition = "datetime")
	private String endDate;
	@Column(name = "stage", columnDefinition = "int")
	private int stage;
	@Column(name = "clock", columnDefinition = "time")
	private String clock;
	@Column(name = "halftime", columnDefinition = "tinyint(1)")
	private Boolean halftime;
	@Column(name = "status", columnDefinition = "varchar(50)")
	private String status;
	@Column(name = "current_period", columnDefinition = "int")
	private int currentPeriod;
	@Column(name = "total_periods", columnDefinition = "int")
	private int totalPeriods;
	@Column(name = "area_name", columnDefinition = "varchar(100)")
	private String areaName;
	@Column(name = "arena_city", columnDefinition = "varchar(100)")
	private String arenaCity;
	@Column(name = "arena_state", columnDefinition = "varchar(2)")
	private String arenaState;
	@Column(name = "arena_country", columnDefinition = "varchar(50)")
	private String arenaCountry;
	@Column(name = "q1h", columnDefinition = "int")
	private int q1h;
	@Column(name = "q2h", columnDefinition = "int")
	private int q2h;
	@Column(name = "q3h", columnDefinition = "int")
	private int q3h;
	@Column(name = "q4h", columnDefinition = "int")
	private int q4h;
	@Column(name = "q1a", columnDefinition = "int")
	private int q1a;
	@Column(name = "q2a", columnDefinition = "int")
	private int q2a;
	@Column(name = "q3a", columnDefinition = "int")
	private int q3a;
	@Column(name = "q4a", columnDefinition = "int")
	private int q4a;


}
