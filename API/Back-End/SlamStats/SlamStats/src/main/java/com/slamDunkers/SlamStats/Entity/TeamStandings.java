package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass( TeamStandingsId.class)
public class TeamStandings {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_ID ",referencedColumnName = "team", nullable = false)
	private Teams team;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "season_ID ", referencedColumnName = "season", nullable = false)
	private Season season;

	@Column(name = "conference_name", columnDefinition = "varchar(10)")
	private String conferenceName;
	@Column(name = "conference_rank", columnDefinition = "int")
	private int conferenceRank;
	@Column(name = "conference_win", columnDefinition = "int")
	private int conferenceWin;
	@Column(name = "conference_loss", columnDefinition = "int")
	private int conferenceLoss;
	@Column(name = "division_games_behind", columnDefinition = "decimal(4,1)")
	private double divisionGamesBehind;
	@Column(name = "home_win", columnDefinition = "int")
	private int homeWin;
	@Column(name = "away_win", columnDefinition = "int")
	private int awayWin;
	@Column(name = "total_win", columnDefinition = "int")
	private int totalWin;
	@Column(name = "win_percentage", columnDefinition = "decimal(4,3)")
	private double winPercentage;
	@Column(name = "last_ten_win", columnDefinition = "int")
	private int lastTenWin;
	@Column(name = "home_loss", columnDefinition = "int")
	private int homeLoss;
	@Column(name = "away_loss", columnDefinition = "int")
	private int awayLoss;
	@Column(name = "total_loss", columnDefinition = "int")
	private int totalLoss;
	@Column(name = "loss_percentage", columnDefinition = "decimal(4,3)")
	private double lossPercentage;
	@Column(name = "last_ten_loss", columnDefinition = "int")
	private int lastTenLoss;
	@Column(name = "games_behind", columnDefinition = "decimal(4,1)")
	private double gamesBehind;
	@Column(name = "streak", columnDefinition = "int")
	private int streak;
	@Column(name = "is_win_streak", columnDefinition = "tinyint(1)")
	private boolean isWinStreak;

}
