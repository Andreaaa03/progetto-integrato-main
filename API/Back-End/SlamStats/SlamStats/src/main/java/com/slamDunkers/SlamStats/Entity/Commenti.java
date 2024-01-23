package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
	private Commenti idCommentoPadre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_utente", columnDefinition = "int")
	private Utente idUtente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_games", columnDefinition = "int")
	private Games idGames;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blog", columnDefinition = "int")
	private Blog blog;

	@Column(name = "creazione", columnDefinition = "timestamp")
	private LocalDateTime data;


}
