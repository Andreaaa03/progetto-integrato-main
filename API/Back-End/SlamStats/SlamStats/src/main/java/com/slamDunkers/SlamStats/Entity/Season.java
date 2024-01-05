package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Season {
	@Id
	@Column(name = "ID", columnDefinition = "int")
	private int Id;
	@Column(name = "year", columnDefinition = "int")
	private int Year;
}
