package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private int id;

	@Column(nullable = false, length = 100)
	private String first_name;
	@Column(nullable = false, length = 100)
	private String last_name;
	@Column(nullable = false)
	private Date birth_date;
	@Column(nullable = false, length = 255, unique = true)
	private String email;
	@Column(nullable = false, length = 255)
	private String pswd;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Roles role_id;

	public Utente(String first_name, String last_name, Date birth_date, String email, String pswd, Roles roles) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.email = email;
		this.pswd = pswd;
		this.role_id = roles;
	}
}
