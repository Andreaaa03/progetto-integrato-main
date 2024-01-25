package com.example.popolamento.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Scores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teams_ID ", nullable = false)
	private Teams team;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "games_ID ", nullable = false)
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
