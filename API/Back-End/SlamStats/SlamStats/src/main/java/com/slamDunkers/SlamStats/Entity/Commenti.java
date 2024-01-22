package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Commenti {

	@Id
	@Column(name = "id", columnDefinition = "int")
	private int id;

	@Column(name = "testo", columnDefinition = "varchar(1000)")
	private String testo;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_commento_padre", columnDefinition = "int")
	private Commenti id_commento_padre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_utente", columnDefinition = "int")
	private Utente id_utente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_games", columnDefinition = "int")
	private Games id_games;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blog", columnDefinition = "int")
	private Blog blog;


}
