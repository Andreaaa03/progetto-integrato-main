package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass( ScoresId.class)
public class Scores {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teams_ID ",referencedColumnName = "team", nullable = false)
	private Teams team;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "games_ID ",referencedColumnName = "game", nullable = false)
	private Games game;

	@Column(name = "win", columnDefinition = "int")
	private int win;
	@Column(name = "loss", columnDefinition = "int")
	private int loss;
	@Column(name = "series_win", columnDefinition = "int")
	private int seriesWin;
	@Column(name = "series_loss", columnDefinition = "int")
	private int seriesLoss;
	@Column(name = "points", columnDefinition = "int")
	private int points;

}
