package com.example.popolamento.Entity;



import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Teams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", columnDefinition = "varchar(255)")
	private String teamName;

	@Column(name = "nickname", columnDefinition = "varchar(255)")
	private String Nickname;

	@Column(name = "code", columnDefinition = "varchar(255)")
	private String Code;
	@Column(name = "city", columnDefinition = "varchar(255)")
	private String City;

	@Column(name = "logo", columnDefinition = "varchar(255)" )
	private String Logo;

	@Column(name = "all_star", columnDefinition = "tinyint(1)")
	private boolean AllStar;

	@Column(name = "nbaFranchise", columnDefinition = "tinyint(1)")
	private boolean nbaFranchise;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="league_id", nullable = false)
	private League league;





}
