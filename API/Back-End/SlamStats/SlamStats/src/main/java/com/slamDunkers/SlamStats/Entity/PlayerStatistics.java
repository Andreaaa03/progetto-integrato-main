package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass( PlayerStatisticsId.class)
public class PlayerStatistics {
//	1 	player_ID Primaria 	int(11) 			No 	Nessuno 			Modifica Modifica 	Elimina Elimina
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_ID ",referencedColumnName = "player", nullable = false)
	private Player player;
//	2 	teams_ID PrimariaIndice 	int(11) 			No 	Nessuno 			Modifica Modifica 	Elimina Elimina
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_ID ",referencedColumnName = "team", nullable = false)
	private Teams team;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_ID ",referencedColumnName = "game", nullable = false)
	private Games game;
	@Column(name = "points", columnDefinition = "int")
	private int points;
	@Column(name = "pos", columnDefinition = "varchar(2)")
	private String pos;
	@Column(name = "min", columnDefinition = "time")
	private String min;
	@Column(name = "fgm", columnDefinition = "int")
	private int fgm;
	@Column(name = "fga", columnDefinition = "int")
	private int fga;
	@Column(name = "fgp", columnDefinition = "decimal(3,2)")
	private double fgp;
	@Column(name = "ftm", columnDefinition = "int")
	private int ftm;
	@Column(name = "fta", columnDefinition = "int")
	private int fta;
	@Column(name = "ftp", columnDefinition = "decimal(3,2)")
	private double ftp;
	@Column(name = "tpm", columnDefinition = "int")
	private int tpm;
	@Column(name = "tpa", columnDefinition = "int")
	private int tpa;
	@Column(name = "tpp", columnDefinition = "decimal(3,2)")
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
