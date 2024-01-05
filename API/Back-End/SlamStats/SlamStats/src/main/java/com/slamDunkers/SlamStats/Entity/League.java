package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class League {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "int")
	private Integer Id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conference_id")
	private Conference conferenceId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "division_id")
	private Division divisionId;

	@Column(name = "name", columnDefinition = "varchar(255) ")
	private String Name;

	public String getConference() {
		return this.conferenceId.getName();
	}

	public String getDivision() {
		return this.divisionId.getName();
	}
}
