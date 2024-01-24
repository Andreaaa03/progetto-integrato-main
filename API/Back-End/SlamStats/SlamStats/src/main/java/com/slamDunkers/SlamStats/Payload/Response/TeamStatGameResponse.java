package com.slamDunkers.SlamStats.Payload.Response;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamStatGameResponse {

	@JsonIgnore
	public int fgm;
	@JsonIgnore
	public int fga;
	@JsonIgnore
	public double fgp;
	@JsonIgnore
	public int ftm;
	@JsonIgnore
	public int fta;
	@JsonIgnore
	public double ftp;
	@JsonIgnore
	public int tpm;
	@JsonIgnore
	public int tpa;
	@JsonIgnore
	public double tpp;
	@JsonIgnore
	public int offReb;
	@JsonIgnore
	public int defReb;
	@JsonIgnore
	public int totReb;
	@JsonIgnore
	public int assists;
	@JsonIgnore
	public int pFouls;
	@JsonIgnore
	public int steals;
	@JsonIgnore
	public int turnovers;
	@JsonIgnore
	public int blocks;
	@JsonIgnore
	public int plusMinus;


	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	public Map<String, Object> dati = new LinkedHashMap<>();
	public QaurtiScoreResponse qaurtiScoreResponse;
	public List<PlayerStatisticsResponse> playersStatistics;

	public void toMap( ) {
		this.dati.put("fgm", this.fgm);
		this.dati.put("fga", this.fga);
		this.dati.put("fgp", this.fgp);
		this.dati.put("ftm", this.ftm);
		this.dati.put("fta", this.fta);
		this.dati.put("ftp", this.ftp);
		this.dati.put("tpm", this.tpm);
		this.dati.put("tpa", this.tpa);
		this.dati.put("tpp", this.tpp);
		this.dati.put("offReb", this.offReb);
		this.dati.put("defReb", this.defReb);
		this.dati.put("totReb", this.totReb);
		this.dati.put("assists", this.assists);
		this.dati.put("pFouls", this.pFouls);
		this.dati.put("steals", this.steals);
		this.dati.put("turnovers", this.turnovers);
		this.dati.put("blocks", this.blocks);
		this.dati.put("plusMinus", this.plusMinus);

	}

}


