package com.slamDunkers.SlamStats.Entity;

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
public class ScoresId implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teams_ID ", nullable = false)
	private Teams team;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "games_ID ", nullable = false)
	private Games game;
}
