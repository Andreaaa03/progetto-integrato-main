package com.example.popolamento.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TeamsStatisticsId implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_ID ", nullable = false)
	private Teams team;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "season_ID ", nullable = false)
	private Season season;

}
