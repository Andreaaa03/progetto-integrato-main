package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass(TeamsStatisticsId.class)
public class TeamsStatistics {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_ID ",referencedColumnName = "team", nullable = false)
	private Teams team;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "season_ID ", referencedColumnName = "season", nullable = false)
	private Season season;

	@Column(name = "games", columnDefinition = "int")
	private int games;
	@Column(name = "fast_break_points", columnDefinition = "int")
	private int fastBreakPoints;
	@Column(name = "points_in_paint", columnDefinition = "int")
	private int pointsInPaint;
	@Column(name = "biggest_lead", columnDefinition = "int")
	private int biggestLead;
	@Column(name = "second_chance_points", columnDefinition = "int")
	private int secondChancePoints;
	@Column(name = "points_off_turnover", columnDefinition = "int")
	private int pointsOffTurnover;
	@Column(name = "points", columnDefinition = "int")
	private int points;
	@Column(name = "fgm", columnDefinition = "int")
	private int fgm;
	@Column(name = "fga", columnDefinition = "int")
	private int fga;
	@Column(name = "fgp", columnDefinition = "decimal(4,1)")
	private double fgp;
	@Column(name = "ftm", columnDefinition = "int")
	private int ftm;
	@Column(name = "fta", columnDefinition = "int")
	private int fta;
	@Column(name = "ftp", columnDefinition = "decimal(4,1)")
	private double ftp;
	@Column(name = "tpm", columnDefinition = "int")
	private int tpm;
	@Column(name = "tpa", columnDefinition = "int")
	private int tpa;
	@Column(name = "tpp", columnDefinition = "decimal(4,1)")
	private double tpp;
	@Column(name = "off_reb", columnDefinition = "int")
	private int offReb;
	@Column(name = "def_reb", columnDefinition = "int")
	private int defReb;
	@Column(name = "tot_reb", columnDefinition = "int")
	private int totReb;
	@Column(name = "assists", columnDefinition = "int")
	private int assists;
	@Column(name = "p_fouls", columnDefinition = "int")
	private int pFouls;
	@Column(name = "steals", columnDefinition = "int")
	private int steals;
	@Column(name = "turnovers", columnDefinition = "int")
	private int turnovers;
	@Column(name = "blocks", columnDefinition = "int")
	private int blocks;
	@Column(name = "plus_minus", columnDefinition = "int")
	private int plusMinus;

}
