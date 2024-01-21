package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Entity.UtenteTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Utente_TeamRepository extends JpaRepository<UtenteTeam, Integer> {
	@Override
	UtenteTeam save(UtenteTeam utente_team);

	Optional<UtenteTeam> findByIdUtenteAndIdTeam(Utente utente, Teams teams);

	 Optional<List<UtenteTeam>> findByIdUtente(Utente utente);

}
