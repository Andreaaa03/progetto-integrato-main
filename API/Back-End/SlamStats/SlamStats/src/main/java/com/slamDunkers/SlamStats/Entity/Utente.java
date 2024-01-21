package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private int id;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "pswd", nullable = false)
	private String passwd;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Roles roleId;

	@Column(name = "data_iscrizione", updatable = false)
	private LocalDate dataIscrizione;
	@Column(name = "numero_telefono", nullable = false)
	private String numeroTelefono;
	@Column(name = "follower")
	private int follower;
	@Column(name = "favorite_team")
	private int favoriteTeam;
	@Column(name = "favorite_player")
	private int favoritePlayer;
	@Column(name ="username" )
	private String username;
}
