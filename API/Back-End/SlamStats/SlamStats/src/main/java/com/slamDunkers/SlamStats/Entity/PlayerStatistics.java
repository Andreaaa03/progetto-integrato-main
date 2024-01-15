package com.slamDunkers.SlamStats.Entity;

import com.slamDunkers.SlamStats.Payload.Response.PlayerStatisticsResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@IdClass( PlayerStatisticsId.class)
public class PlayerStatistics {
//	1 	player_ID Primaria 	int(11) 			No 	Nessuno 			Modifica Modifica 	Elimina Elimina
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_ID",referencedColumnName = "player", nullable = false)
	private Player player;
//	2 	teams_ID PrimariaIndice 	int(11) 			No 	Nessuno 			Modifica Modifica 	Elimina Elimina
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teams_id",referencedColumnName = "team", nullable = false)
	private Teams team;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id",referencedColumnName = "game", nullable = false)
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
	@Column(name = "fgp", columnDefinition = "double")
	private double fgp;
	@Column(name = "ftm", columnDefinition = "int")
	private int ftm;
	@Column(name = "fta", columnDefinition = "int")
	private int fta;
	@Column(name = "ftp", columnDefinition = "double")
	private double ftp;
	@Column(name = "tpm", columnDefinition = "int")
	private int tpm;
	@Column(name = "tpa", columnDefinition = "int")
	private int tpa;
	@Column(name = "tpp", columnDefinition = "double")
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

	public PlayerStatisticsResponse toPlayerStatisticsResponse(){
		PlayerStatisticsResponse response = new PlayerStatisticsResponse();
		response.setId(this.player.getId());
		response.setNome(this.player.getFirstName() + " " + this.player.getLastName());
		response.setPoints(this.points);
		response.setPos(this.pos);
		response.setMin(this.min);
		response.setFgm(this.fgm);
		response.setFga(this.fga);
		response.setFgp(this.fgp);
		response.setFtm(this.ftm);
		response.setFta(this.fta);
		response.setFtp(this.ftp);
		response.setTpm(this.tpm);
		response.setTpa(this.tpa);
		response.setTpp(this.tpp);
		response.setOffReb(this.offReb);
		response.setDefReb(this.defReb);
		response.setTotReb(this.totReb);
		response.setAssists(this.assists);
		response.setPFouls(this.pFouls);
		response.setSteals(this.steals);
		response.setTurnovers(this.turnovers);
		response.setBlocks(this.blocks);
		response.setPlusMinus(this.plusMinus);
		return response;
	}


}
